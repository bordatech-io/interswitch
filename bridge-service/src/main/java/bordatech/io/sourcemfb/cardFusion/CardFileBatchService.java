package bordatech.io.sourcemfb.cardFusion;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CardFileBatchService {
    private static final Logger logger = LogManager.getLogger(CardFileBatchService.class);
    private static final int ACCOUNT_NUMBER_LENGTH = 7;
    private static final String DEFAULT_PAN_BIN = "50613602065";

    private static final String DEFAULT_ISSUER_CODE = "001";
    private static final String DEFAULT_CARD_TYPE = "1";
    private static final String DEFAULT_CURRENCY = "NGN";
    private static final String DEFAULT_ACCOUNT_PRODUCT_CODE = "203";
    private static final String DEFAULT_OPENING_LEDGER_BAL = "0";
    private static final String DEFAULT_OPENING_AVAIL_BAL = "0";
    private static final DateTimeFormatter DT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final List<String> REQUIRED_HEADERS = List.of(
            "action", "product_name", "expiry_yymm", "status",
            "profile_code", "emboss_short_name", "emboss_full_name",
            "branch_name", "kyc_level", "block_flag", "sequence"
    );
    private final Random random = new Random();
    private final String bin;

    public CardFileBatchService(@Value("${BIN:50613603093}") String bin) {
        String configuredBin = nz(bin).replaceAll("\\s+", "");
        if (configuredBin.matches("\\d{8,11}")) {
            this.bin = configuredBin;
        } else {
            logger.warn("Invalid BIN configuration '{}'. Falling back to default BIN {}", configuredBin, DEFAULT_PAN_BIN);
            this.bin = DEFAULT_PAN_BIN;
        }
    }

    public byte[] generateCardFilesZip(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file is required and must not be empty");
        }

        StringBuilder cards = new StringBuilder();
        StringBuilder cardAccounts = new StringBuilder();
        StringBuilder accounts = new StringBuilder();
        StringBuilder accountBalances = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException("Input CSV is empty.");
            }

            Map<String, Integer> headerMap = parseHeader(headerLine);
            validateHeader(headerMap);

            boolean hasOpeningLedger = headerMap.containsKey("opening_ledger_balance");
            boolean hasOpeningAvail = headerMap.containsKey("opening_available_balance");
            boolean hasNuban = headerMap.containsKey("nuban");
            boolean hasLinkedAccount = headerMap.containsKey("linked_account");

            String line;
            int rowNum = 1;
            while ((line = br.readLine()) != null) {
                rowNum++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] cols = splitCsv(line);
                InputRow row = toRow(cols, headerMap, hasNuban, hasLinkedAccount, hasOpeningLedger, hasOpeningAvail);
                row.pan = panGenerator();
                try {
                    validateRow(row);
                } catch (Exception e) {
                    logger.warn("Skipping invalid row {}: {}", rowNum, e.getMessage());
                    continue;
                }

                String createdAt = now();
                String updatedAt = createdAt;

                cards.append(buildCardsLine(
                        row.action, row.pan, DEFAULT_ISSUER_CODE, row.productName,
                        DEFAULT_CARD_TYPE, row.expiryYYMM, row.status, row.profileCode,
                        row.linkedAccount, row.embossShortName, row.embossFullName, row.branchName,
                        DEFAULT_CURRENCY, row.kycLevel, row.blockFlag,
                        createdAt, updatedAt, DEFAULT_ISSUER_CODE, row.sequence
                )).append('\n');

                String accountProductCode = deriveAccountProductCode(row.linkedAccount);
                cardAccounts.append(buildCardAccountsLine(
                        row.action, row.pan, row.linkedAccount, accountProductCode
                )).append('\n');

                accounts.append(buildAccountsLine(
                        row.action, row.linkedAccount, DEFAULT_CURRENCY, row.sequence
                )).append('\n');

                String openingLedger = nz(row.openingLedgerBalance).isEmpty() ? DEFAULT_OPENING_LEDGER_BAL : row.openingLedgerBalance;
                String openingAvail = nz(row.openingAvailableBalance).isEmpty() ? DEFAULT_OPENING_AVAIL_BAL : row.openingAvailableBalance;
                accountBalances.append(buildAccountBalancesLine(
                        row.action, row.linkedAccount, openingLedger, openingAvail
                )).append('\n');
            }
        }

        return zipCardFiles(
                cards.toString(),
                cardAccounts.toString(),
                accounts.toString(),
                accountBalances.toString()
        );
    }

    private static byte[] zipCardFiles(String cards, String cardAccounts, String accounts, String accountBalances) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos, StandardCharsets.UTF_8)) {

            zos.putNextEntry(new ZipEntry("CARDFILES/"));
            zos.closeEntry();

            writeZipEntry(zos, "CARDFILES/CARDS.TXT", cards);
            writeZipEntry(zos, "CARDFILES/CARDACCOUNTS.TXT", cardAccounts);
            writeZipEntry(zos, "CARDFILES/ACCOUNTS.TXT", accounts);
            writeZipEntry(zos, "CARDFILES/ACCOUNTBALANCES.TXT", accountBalances);

            zos.finish();
            return baos.toByteArray();
        }
    }

    private static void writeZipEntry(ZipOutputStream zos, String entryName, String content) throws IOException {
        zos.putNextEntry(new ZipEntry(entryName));
        zos.write(content.getBytes(StandardCharsets.UTF_8));
        zos.closeEntry();
    }

    private static String buildCardAccountsLine(String action, String pan, String accountNumber, String accountProductCode) {
        return String.join(",", nz(action), nz(pan), "", nz(accountNumber), "", nz(accountProductCode));
    }

    private static String buildAccountsLine(String action, String accountNumber, String currency, String sequence) {
        return String.join(",", nz(action), nz(accountNumber), "", nz(currency), nz(sequence));
    }

    private static String buildAccountBalancesLine(String action, String accountNumber, String ledgerBalance, String availableBalance) {
        return String.join(",", nz(action), nz(accountNumber), nz(ledgerBalance), nz(availableBalance));
    }

    private static String buildCardsLine(
            String action, String pan, String issuerCode, String product,
            String cardType, String expiryYYMM, String status, String profileCode,
            String linkedAccount, String embossShort, String embossFull, String branchName,
            String currency, String kycLevel, String blockFlag,
            String createdAt, String updatedAt, String issuerCode2, String sequence
    ) {
        String[] f = new String[42];

        f[0] = nz(action);
        f[1] = nz(pan);
        f[2] = nz(issuerCode);
        f[3] = nz(product);
        f[4] = "";
        f[5] = nz(cardType);
        f[6] = "";
        f[7] = nz(expiryYYMM);
        f[8] = "";
        f[9] = "";
        f[10] = "";
        f[11] = "";
        f[12] = nz(status);
        f[13] = nz(profileCode);
        f[14] = "";
        f[15] = "";
        f[16] = nz(linkedAccount);
        f[17] = "";
        f[18] = "";
        f[19] = "";
        f[20] = "";
        f[21] = nz(embossShort);
        f[22] = nz(embossFull);
        f[23] = nz(branchName);
        f[24] = "";
        f[25] = "";
        f[26] = "";
        f[27] = "";
        f[28] = "";
        f[29] = nz(currency);
        f[30] = "";
        f[31] = nz(kycLevel);
        f[32] = "";
        f[33] = nz(blockFlag);
        f[34] = "";
        f[35] = "";
        f[36] = "";
        f[37] = nz(createdAt);
        f[38] = nz(updatedAt);
        f[39] = "";
        f[40] = nz(issuerCode2);
        f[41] = nz(sequence);

        return String.join(",", f);
    }

    private static Map<String, Integer> parseHeader(String headerLine) {
        String[] headers = splitCsv(headerLine);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            map.put(unquote(headers[i]).trim().toLowerCase(Locale.ROOT), i);
        }
        return map;
    }

    private static void validateHeader(Map<String, Integer> headerMap) {
        List<String> missing = new ArrayList<>();
        for (String h : REQUIRED_HEADERS) {
            if (!headerMap.containsKey(h)) {
                missing.add(h);
            }
        }
        if (!missing.isEmpty()) {
            throw new IllegalArgumentException("Missing required headers: " + missing);
        }
        if (!headerMap.containsKey("nuban") && !headerMap.containsKey("linked_account")) {
            throw new IllegalArgumentException("Missing required account column: provide nuban (preferred) or linked_account");
        }
    }

    private static InputRow toRow(String[] cols,
                                  Map<String, Integer> headerMap,
                                  boolean hasNuban,
                                  boolean hasLinkedAccount,
                                  boolean hasOpeningLedger,
                                  boolean hasOpeningAvail) {
        InputRow r = new InputRow();
        r.action = get(cols, headerMap, "action");
        r.productName = get(cols, headerMap, "product_name");
        r.expiryYYMM = get(cols, headerMap, "expiry_yymm");
        r.status = get(cols, headerMap, "status");
        r.profileCode = get(cols, headerMap, "profile_code");
        String nuban = hasNuban ? get(cols, headerMap, "nuban") : "";
        String linkedAccount = hasLinkedAccount ? get(cols, headerMap, "linked_account") : "";
        r.linkedAccount = nz(nuban).isEmpty() ? linkedAccount : nuban;
        r.embossShortName = get(cols, headerMap, "emboss_short_name");
        r.embossFullName = get(cols, headerMap, "emboss_full_name");
        r.branchName = get(cols, headerMap, "branch_name");
        r.kycLevel = get(cols, headerMap, "kyc_level");
        r.blockFlag = get(cols, headerMap, "block_flag");
        r.sequence = get(cols, headerMap, "sequence");
        r.openingLedgerBalance = hasOpeningLedger ? get(cols, headerMap, "opening_ledger_balance") : "";
        r.openingAvailableBalance = hasOpeningAvail ? get(cols, headerMap, "opening_available_balance") : "";
        return r;
    }

    private static void validateRow(InputRow r) {
        r.action = nz(r.action).toUpperCase(Locale.ROOT);
        if (!List.of("I", "U", "D").contains(r.action)) {
            throw new IllegalArgumentException("action must be I/U/D");
        }
        if (!nz(r.pan).matches("\\d{16,19}")) {
            throw new IllegalArgumentException("pan must be 16-19 digits");
        }
        if (!luhnValid(r.pan)) {
            throw new IllegalArgumentException("pan failed Luhn check");
        }
        if (!nz(r.expiryYYMM).matches("\\d{4}")) {
            throw new IllegalArgumentException("expiry_yymm must be YYMM (4 digits)");
        }
        if (nz(r.productName).isEmpty()) {
            throw new IllegalArgumentException("product_name is required");
        }
        if (nz(r.profileCode).isEmpty()) {
            throw new IllegalArgumentException("profile_code is required");
        }
        if (!nz(r.linkedAccount).matches("\\d+")) {
            throw new IllegalArgumentException("linked_account must be numeric account number");
        }
        if (nz(r.embossShortName).isEmpty()) {
            throw new IllegalArgumentException("emboss_short_name is required");
        }
        if (nz(r.embossFullName).isEmpty()) {
            throw new IllegalArgumentException("emboss_full_name is required");
        }
        if (nz(r.branchName).isEmpty()) {
            throw new IllegalArgumentException("branch_name is required");
        }
        if (!nz(r.kycLevel).matches("\\d+")) {
            throw new IllegalArgumentException("kyc_level must be numeric");
        }
        if (!List.of("0", "1").contains(nz(r.blockFlag))) {
            throw new IllegalArgumentException("block_flag must be 0 or 1");
        }
        if (!nz(r.sequence).matches("\\d{1,12}")) {
            throw new IllegalArgumentException("sequence must be numeric");
        }
        if (!nz(r.openingLedgerBalance).isEmpty() && !isNumber(r.openingLedgerBalance)) {
            throw new IllegalArgumentException("opening_ledger_balance must be numeric if provided");
        }
        if (!nz(r.openingAvailableBalance).isEmpty() && !isNumber(r.openingAvailableBalance)) {
            throw new IllegalArgumentException("opening_available_balance must be numeric if provided");
        }
    }

    private static String[] splitCsv(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
                continue;
            }
            if (c == ',' && !inQuotes) {
                values.add(unquote(current.toString()));
                current.setLength(0);
                continue;
            }
            current.append(c);
        }
        values.add(unquote(current.toString()));
        return values.toArray(new String[0]);
    }

    private static String unquote(String value) {
        String s = value == null ? "" : value.trim();
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1);
        }
        return s.replace("\"\"", "\"");
    }

    private static String get(String[] cols, Map<String, Integer> headerMap, String header) {
        Integer idx = headerMap.get(header);
        if (idx == null || idx >= cols.length) {
            return "";
        }
        return nz(cols[idx]);
    }

    private static String now() {
        return LocalDateTime.now().format(DT);
    }

    private static String nz(String s) {
        return s == null ? "" : s.trim();
    }

    private static String deriveAccountProductCode(String accountNumber) {
        String acc = nz(accountNumber);
        if (acc.length() >= 3) {
            return acc.substring(0, 3);
        }
        return DEFAULT_ACCOUNT_PRODUCT_CODE;
    }

    private static boolean isNumber(String s) {
        return nz(s).matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean luhnValid(String number) {
        int sum = 0;
        boolean alt = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = number.charAt(i) - '0';
            if (alt) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alt = !alt;
        }
        return sum % 10 == 0;
    }

    private String panGenerator() {
        String accountNumber = generateRandomAccountNumber(ACCOUNT_NUMBER_LENGTH);
        String panWithoutCheckDigit = bin + accountNumber;
        String pan = completePan(panWithoutCheckDigit);
        if (pan.isEmpty()) {
            throw new IllegalArgumentException("Could not generate valid PAN");
        }
        logger.info("Generated PAN: {}", pan);
        return pan;
    }

    private String completePan(String generated) {
        for (int i = 0; i <= 9; i++) {
            String candidate = generated + i;
            if (isValidPan(candidate)) {
                return candidate;
            }
        }
        return "";
    }

    private String generateRandomAccountNumber(int n) {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < n; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    private boolean isValidPan(String pan) {
        return luhnValid(pan);
    }

    private static class InputRow {
        String action;
        String pan;
        String productName;
        String expiryYYMM;
        String status;
        String profileCode;
        String linkedAccount;
        String embossShortName;
        String embossFullName;
        String branchName;
        String kycLevel;
        String blockFlag;
        String sequence;
        String openingLedgerBalance;
        String openingAvailableBalance;
    }
}

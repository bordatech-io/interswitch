package bordatech.io.sourcemfb.iso8583;


import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.DebitRequest;
import bordatech.io.sourcemfb.request.model.ReversalRequest;
import bordatech.io.sourcemfb.request.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.lang.reflect.Method;


@Service
public class IsoHelper {
    @Autowired
    CbaMiddleWare cbaMiddleWare;
    public static String composeField54(double ledgerBalance, double availableBalance, String BalType) {
        // Constants for the field composition
        String accountType = "10";
        String currencyCode = "566"; // NGN
        String ledgerAmountType = "01";
        String availableAmountType = "02";
        String amountSignC = "C";
        String amountSignD = "D";
        String amountSign = "";

        // Format the amounts to 12-digit numbers with 3 decimal places
        String formattedLedgerBalance = formatAmount(ledgerBalance);
        String formattedAvailableBalance = formatAmount(availableBalance);

        // Compose the ledger balance field
        String ledgerBalanceField = accountType + ledgerAmountType + currencyCode + amountSignC + formattedLedgerBalance;

        // Compose the available balance field
        String availableBalanceField = accountType + availableAmountType + currencyCode + amountSignC + formattedAvailableBalance;

        if(BalType.equals("30")){
            return addLengthPrefix(availableBalanceField);
        }
        if(BalType.equals("31")){
            return addLengthPrefix(ledgerBalanceField);
        }
        // Concatenate both fields
        String combinedField = ledgerBalanceField + availableBalanceField;

        // If ledger balance equals available balance, return only ledger balance
        if (Double.compare(ledgerBalance, availableBalance) == 0) {
            return addLengthPrefix(ledgerBalanceField);
        }

        // Return the combined field with length prefix
        return addLengthPrefix(combinedField);
    }

    // Helper method to format the amount to a 12-digit string with 3 decimal places
//    private static String formatAmount(double amount) {
//        return String.format("%012.3f", amount).replace(".", "");
//    }

    private static String formatAmount(double amount) {
//        return String.format("%012d", (int)(amount * 1000));
        DecimalFormat df = new DecimalFormat("0000000000.00");
        String formatted = df.format(amount);
        return formatted.replace(".", "");
    }

    // Helper method to add the length prefix
    private static String addLengthPrefix(String field) {
        int length = field.length();
        String lengthPrefix = String.format("%03d", length);
        return lengthPrefix + field;
    }

    public static String pan2Account(String Pan) {
        String acc = "";
        if (Pan.equals("5061360206564676447")) {
            acc = "2031235682";
        } else if (Pan.equals("5061360206556992513")) {
            acc = "2037949672";
        } else if (Pan.equals("5061360206512438718")) {
            acc = "2036164568";
        }else {
            acc = "2031657119";
        }
        return acc;
    }

    public static String formatLeadingC(String value) {
        if (value != null && (value.startsWith("C") || value.startsWith("D"))) {
            return value.substring(1);
        }
        if (value != null && value.matches("^0+(?!$).*")) {
            value = value.replaceFirst("^0+(?!$)", "");
        }
        return value;
    }

    public String doInternalDebit(String mode,
                                  String nuban,
                                  double amount,
                                  double chargeAmount,
                                  String referenceNumber,
                                  String narration) {

        String response = "00";
        System.out.println("--------------Transaction mode----------------------");
        System.out.println(mode);
        String drCrValue = (mode.equalsIgnoreCase("reversal")) ? "cr" : "db";
        String refPatch = (mode.equalsIgnoreCase("reversal")) ? "0420" : "0200";
        System.out.println(drCrValue);
        System.out.println("--------------Transaction mode----------------------");
        if(drCrValue.equals("db")){
            DebitRequest debitRequest = new DebitRequest();
            debitRequest.setAccountnumber(nuban);
            debitRequest.setNarration(narration);
            debitRequest.setTransactionreference(referenceNumber);
            debitRequest.setAmount(amount);
            TransactionResponse transactionResponse = cbaMiddleWare.debit(debitRequest);
            response= transactionResponse.getCode();
        }else {
            ReversalRequest reversalRequest =  new ReversalRequest();
            reversalRequest.setTransactionreference(referenceNumber);
            reversalRequest.setAccountnumber(nuban);
            TransactionResponse transactionResponse = cbaMiddleWare.reversal(reversalRequest);
            response = transactionResponse.getCode();
        }




//
    return response;
    }

    public static double extractFeeOrZero(String field28) {
        if (field28 == null || field28.length() != 9) {
            return 0.0;
        }

        char sign = field28.charAt(0);
        String amountPart = field28.substring(1);

        // must be numeric
        if (!amountPart.chars().allMatch(Character::isDigit)) {
            return 0.0;
        }

        double fee = Double.parseDouble(amountPart) / 100.0; // kobo → naira

        if (sign == 'C') {
            return -fee; // credit / reversal / waived
        }

        if (sign == 'D') {
            return fee;  // debit
        }

        return 0.0;
    }

    public  String formatField(String input, int FIELD_43_LENGTH) {

        if (input.length() > FIELD_43_LENGTH) {
            // Trim to 40 characters if it's too long
            return input.substring(0, FIELD_43_LENGTH);
        } else {
            // Pad with spaces to ensure it is exactly 40 characters
            return String.format("%-" + FIELD_43_LENGTH + "s", input);
        }
    }


    public static void copyNonNullProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }

        Method[] methods = source.getClass().getMethods();

        for (Method getter : methods) {
            if (getter.getName().startsWith("get") && getter.getParameterCount() == 0) {
                try {
                    Object value = getter.invoke(source);
                    if (value != null ) {
                        String setterName = getter.getName().replaceFirst("get", "set");
                        if(!(setterName.equals("setMti") || setterName.equals("Mti"))){
                            try {
                                Method setter = target.getClass().getMethod(setterName, getter.getReturnType());
                                setter.invoke(target, value);
                            } catch (NoSuchMethodException ignored) {
                                // Ignore if no matching setter exists
                                System.out.println(ignored.toString());
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions as needed
                }
            }
        }
    }

}

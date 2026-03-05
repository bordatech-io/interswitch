package bordatech.io.sourcemfb.utils;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class TransactionIdGenerator {

    // Method to generate a unique transaction ID
    public static String generateTransactionId(String senderBankCode) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        String uniqueNumber = generateRandomNumber();
        return senderBankCode.substring(0, Math.min(senderBankCode.length(), 6)) + // Ensure sender bank code is not longer than 6 characters
                formattedDateTime + // Take only the first 8 characters of the formatted date and time
                uniqueNumber.substring(0, Math.min(uniqueNumber.length(), 12));
    }

    // Method to generate a random 12-digit number
    private static String generateRandomNumber() {
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            randomNumber.append(random.nextInt(10)); // Generate random digit (0-9)
        }
        return randomNumber.toString();
    }
}

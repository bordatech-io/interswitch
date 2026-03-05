package bordatech.io.sourcemfb.utils;

import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateTimeProvider {

    // Method to provide a Timestamp for the current date and time
    public String getCurrentTimestamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return Timestamp.valueOf(currentDateTime).toString();
    }
}

package tutoring.Project.util;

import java.sql.Timestamp;

public class DateTimeFormatter {
    
    protected String format = "Y-M-d H:m:s";
    
    public String getDateTime(
        Timestamp timestamp,
        String format
    ) {
        return timestamp.toLocalDateTime()
                        .format(java.time.format.DateTimeFormatter.ofPattern(format));
    }
    
    public String getDateTime(
        Timestamp localDateTime
    ) {
        return this.getDateTime(localDateTime, this.format);
    }
}

package tutoring.Project.util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    
    protected String format = "Y-m-d";
    
    public String getDateTime(
        Timestamp timestamp,
        String format
    ) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(format));
    }
    
    public String getDateTime(
        Timestamp localDateTime
    ) {
        return this.getDateTime(localDateTime, this.format);
    }
}

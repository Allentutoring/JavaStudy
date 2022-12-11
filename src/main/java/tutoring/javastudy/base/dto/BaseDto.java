package tutoring.javastudy.base.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tutoring.javastudy.util.DateTimeFormatter;

@SuperBuilder
@NoArgsConstructor
public class BaseDto {
    
    protected DateTimeFormatter dateTimeUtil;
    
    {
        this.dateTimeUtil = new DateTimeFormatter();
    }
    
}

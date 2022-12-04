package tutoring.javastudy.base.dto;

import lombok.NoArgsConstructor;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.util.DateTimeFormatter;

@NoArgsConstructor
public class BaseResponseDto<Entity extends BaseEntity> {
    
    protected DateTimeFormatter dateTimeUtil;
    
    {
        this.dateTimeUtil = new DateTimeFormatter();
    }
    
    public BaseResponseDto(Entity entity)
    {
        this.bindEntity(entity);
    }
    
    public void bindEntity(Entity entity)
    {
    }
}

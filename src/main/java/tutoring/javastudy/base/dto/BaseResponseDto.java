package tutoring.javastudy.base.dto;

import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.util.DateTimeFormatter;

public class BaseResponseDto<Entity extends BaseEntity> {
    
    protected DateTimeFormatter dateTimeUtil;
    
    {
        this.dateTimeUtil = new DateTimeFormatter();
    }
    
    public BaseResponseDto() {
        this.init();
    }
    
    public BaseResponseDto(Entity entity) {
        this.init();
        this.bindEntity(entity);
    }
    
    protected void init() {
    
    }
    
    public void bindEntity(Entity entity) {
    }
}

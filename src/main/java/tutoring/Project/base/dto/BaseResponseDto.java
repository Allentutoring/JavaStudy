package tutoring.Project.base.dto;

import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.util.DateTimeFormatter;

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

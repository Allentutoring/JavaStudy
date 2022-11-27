package tutoring.Project.base.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.util.DateTimeUtil;

@Data
@NoArgsConstructor
abstract public class BaseResponseDto<Entity extends BaseEntity> {
    
    protected DateTimeUtil dateTimeUtil;
    
    {
        this.dateTimeUtil = new DateTimeUtil();
    }
    
    public BaseResponseDto(Entity entity) {
        this.bindEntity(entity);
    }
    
    protected void init() {
    
    }
    
    public abstract void bindEntity(Entity entity);
}

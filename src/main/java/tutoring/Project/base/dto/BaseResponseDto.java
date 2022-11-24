package tutoring.Project.base.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tutoring.Project.base.entity.BaseEntity;

@Data
@NoArgsConstructor
abstract public class BaseResponseDto<Entity extends BaseEntity> {
    
    public BaseResponseDto(Entity entity) {
        bindEntity(entity);
    }
    
    public abstract void bindEntity(Entity entity);
}

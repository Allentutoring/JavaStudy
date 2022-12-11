package tutoring.javastudy.base.response;

import lombok.NoArgsConstructor;
import tutoring.javastudy.base.dto.BaseDto;
import tutoring.javastudy.base.entity.BaseEntity;

@NoArgsConstructor
public class BaseResponseDto<Entity extends BaseEntity> extends BaseDto {
    
    public BaseResponseDto(Entity entity)
    {
        this.bindEntity(entity);
    }
    
    public void bindEntity(Entity entity)
    {
    }
}

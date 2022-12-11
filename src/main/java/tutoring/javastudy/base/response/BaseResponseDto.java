package tutoring.javastudy.base.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import tutoring.javastudy.base.dto.BaseDto;
import tutoring.javastudy.base.entity.BaseEntity;

@NoArgsConstructor
public class BaseResponseDto<Entity extends BaseEntity> extends BaseDto {
    
    @JsonProperty
    protected long id;
    @JsonProperty
    protected String createdAt;
    
    public BaseResponseDto(Entity entity)
    {
        this.bindEntity(entity);
    }
    
    public void bindEntity(Entity entity)
    {
    }
}

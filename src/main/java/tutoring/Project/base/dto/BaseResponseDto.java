package tutoring.Project.base.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tutoring.Project.base.entity.BaseEntity;

@Data
@NoArgsConstructor
public class BaseResponseDto<Entity extends BaseEntity> {

    private Object data;

    public void bindEntity(Entity entity) {
        this.data = entity;
    }
}

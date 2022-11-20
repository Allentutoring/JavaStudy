package tutoring.Project.base.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tutoring.Project.base.entity.BaseEntity;

@Data
@NoArgsConstructor
public class BaseResponseDto {

    private Object data;

    public void bindEntity(BaseEntity entity) {
        this.data = entity;
    }
}

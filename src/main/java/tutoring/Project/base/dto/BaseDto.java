package tutoring.Project.base.dto;

import lombok.Getter;
import tutoring.Project.base.entity.BaseEntity;

abstract public class BaseDto<Entity extends BaseEntity> {
    @Getter
    protected String[] fillable;

    public Entity toEntity() {
//        Entity entity = (Entity) new {Entity}();
//        return entity;
        return null;
    }
}
package tutoring.Project.base;

import lombok.Getter;

abstract public class BaseDto<Entity extends BaseEntity> {
    @Getter
    protected String[] fillable;

    public Entity toEntity() {
//        Entity entity = (Entity) new {Entity}();
//        return entity;
        return null;
    }
}

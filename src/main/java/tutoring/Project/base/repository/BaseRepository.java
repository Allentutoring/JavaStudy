package tutoring.Project.base.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import tutoring.Project.base.entity.BaseEntity;

public interface BaseRepository<Entity extends BaseEntity, Var> extends JpaRepository<Entity, Var> {
    
    public default void softDelete(Entity e) {
        e.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.save(e);
    }
}
package tutoring.Project.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BaseRepository<Entity extends BaseEntity, Var> extends JpaRepository<Entity, Var> {

    public default void softDelete(Entity e) {
        e.setDeletedAt(LocalDateTime.now());
        this.save(e);
    }
}
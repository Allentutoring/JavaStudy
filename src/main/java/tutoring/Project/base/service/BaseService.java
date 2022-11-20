package tutoring.Project.base.service;

import lombok.RequiredArgsConstructor;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseService<Entity extends BaseEntity, Repository extends BaseRepository> {

    private final Repository repository;

    public List<Entity> index() {
        return repository.findAll();
    }

    public Optional<Entity> show(Object id) {
        return repository.findById(id);
    }

    public Object save(Entity entity) {
        return repository.save(entity);
    }

    public Object update(Entity entity) {
        return repository.save(entity);
    }

    public void delete(Entity entity) {
        repository.delete(entity);
    }

}

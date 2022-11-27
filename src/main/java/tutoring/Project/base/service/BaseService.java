package tutoring.Project.base.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.repository.BaseRepository;

@RequiredArgsConstructor
public class BaseService<Entity extends BaseEntity, Repository extends BaseRepository> {
    
    private final Repository repository;
    
    public List<Entity> index() {
        return repository.findAll();
    }
    
    public Optional<Entity> show(Object id) {
        return repository.findById(id);
    }
    
    @Transactional
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

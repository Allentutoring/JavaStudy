package tutoring.javastudy.base.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.base.repository.BaseRepository;

@RequiredArgsConstructor
public class BaseService<Entity extends BaseEntity, Repository extends BaseRepository> {
    
    private final Repository repository;
    
    public List<Entity> index()
    {
        return repository.findAll();
    }
    
    public Page<Entity> index(Pageable pageable)
    {
        return repository.findAll(pageable);
    }
    
    public Optional<Entity> show(Object id)
    {
        return repository.findById(id);
    }
    
    public Object save(Entity entity)
    {
        return repository.save(entity);
    }
    
    public Object update(Entity entity)
    {
        return repository.save(entity);
    }
    
    public void delete(Entity entity)
    {
        repository.delete(entity);
    }
    
}

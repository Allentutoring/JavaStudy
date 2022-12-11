package tutoring.javastudy.base.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.base.repository.BaseRepository;
import tutoring.javastudy.base.service.BaseService;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseController<Entity extends BaseEntity, Repository extends BaseRepository> {
    
    
    protected Entity getEntity()
    {
        return null;
    }
    
    protected BaseService<Entity, Repository> getService()
    {
        return null;
    }
    
    protected Convertable getConverter()
    {
        return null;
    }
}
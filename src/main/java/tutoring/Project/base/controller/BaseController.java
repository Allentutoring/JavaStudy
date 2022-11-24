package tutoring.Project.base.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.board.repository.BoardRepository;
import tutoring.Project.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseController<Entity extends BaseEntity> {
    
    
    protected Entity getEntity() {
        return null;
    }
    
    protected BaseService<Entity, BoardRepository> getService() {
        return null;
    }
    
    protected Convertable getConvertable() {
        return null;
    }
}
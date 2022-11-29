package tutoring.Project.base.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.repository.BaseRepository;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseController<Entity extends BaseEntity, Repository extends BaseRepository> {


    protected Entity getEntity() {
        return null;
    }

    protected BaseService<Entity, Repository> getService() {
        return null;
    }

    protected Convertable getConverter() {
        return null;
    }
}
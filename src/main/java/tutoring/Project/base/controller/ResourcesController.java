package tutoring.Project.base.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.board.repository.BoardRepository;
import tutoring.Project.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
public abstract class ResourcesController<Entity extends BaseEntity> extends
    BaseController<Entity> {
    
    public <ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<List<ResponseDto>> info(
        Class<ResponseDto> responseDtoClass
    ) {
        return ResponseEntity.ok(getService().index().stream().map(e -> {
            ResponseDto response;
            try {
                response = newInstance(responseDtoClass);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
            response.bindEntity(e);
            return response;
        }).collect(Collectors.toList()));
    }
    
    public <ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<ResponseDto> show(
        @PathVariable("id") Entity entity,
        Class<ResponseDto> responseDtoClass
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ResponseDto response = newInstance(responseDtoClass, entity);
        return ResponseEntity.ok(response);
    }
    
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<ResponseDto> store(
        Class<Entity> entityClass,
        RequestDto request,
        Class<ResponseDto> responseDtoClass
    )
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Entity entity = newInstance(entityClass);
        getConvertable().convertDtoToEntity(request, entity);
        getService().save(entity);
        return ResponseEntity.ok(newInstance(responseDtoClass, entity));
    }
    
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<ResponseDto> update(
        Entity entity,
        RequestDto request,
        Class<ResponseDto> responseDtoClass
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        getConvertable().convertDtoToEntity(request, entity);
        getService().save(entity);
        return ResponseEntity.ok(newInstance(responseDtoClass, entity));
    }
    
    public <ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<ResponseDto> delete(
        Entity entity,
        Class<ResponseDto> responseDtoClass
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        getService().delete(entity);
        return ResponseEntity.ok(newInstance(responseDtoClass));
    }
    
    private <Instance> Instance newInstance(Class<Instance> cls)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return cls.getDeclaredConstructor().newInstance();
    }
    
    private <Instance> Instance newInstance(
        Class<Instance> cls,
        Entity entity
    )
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return cls.getDeclaredConstructor(entity.getClass()).newInstance(entity);
    }
    
    abstract protected BaseService<Entity, BoardRepository> getService();
    
    abstract protected Convertable getConvertable();
}

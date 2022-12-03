package tutoring.javastudy.base.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tutoring.javastudy.base.dto.BaseRequestDto;
import tutoring.javastudy.base.dto.BaseResponseDto;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.base.repository.BaseRepository;
import tutoring.javastudy.base.service.BaseService;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
public abstract class ResourcesController<Entity extends BaseEntity, Repository extends BaseRepository> extends
    BaseController<Entity, Repository> {

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
        ResponseDto response = createResponseInstance(responseDtoClass, entity);
        return ResponseEntity.ok(response);
    }

    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<ResponseDto> store(
        Class<Entity> entityClass,
        RequestDto request,
        Class<ResponseDto> responseDtoClass
    )
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Entity entity = newInstance(entityClass);
        getConverter().convertDtoToEntity(request, entity);
        getService().save(entity);
        return ResponseEntity.ok(createResponseInstance(responseDtoClass, entity));
    }

    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto<Entity>> ResponseEntity<ResponseDto> update(
        Entity entity,
        RequestDto request,
        Class<ResponseDto> responseDtoClass
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        getConverter().convertDtoToEntity(request, entity);
        getService().update(entity);
        return ResponseEntity.ok(createResponseInstance(responseDtoClass, entity));
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

    public <ResponseDto extends BaseResponseDto<Entity>> ResponseDto createResponseInstance(
        Class<ResponseDto> cls,
        Entity entity
    )
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ResponseDto dto;
        try {
            dto = cls.getDeclaredConstructor(entity.getClass()).newInstance(entity);
        } catch (NoSuchMethodException noSuchMethodException) {
            dto = cls.getDeclaredConstructor().newInstance();
            dto.bindEntity(entity);
        }
        return dto;
    }

    abstract protected BaseService<Entity, Repository> getService();

    abstract protected Convertable getConverter();
}

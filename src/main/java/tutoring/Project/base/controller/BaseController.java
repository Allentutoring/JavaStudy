package tutoring.Project.base.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.board.repository.BoardRepository;
import tutoring.Project.util.Converter;

@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseController<Entity extends BaseEntity, RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> {
    
    @GetMapping
    public <ResponseDto extends BaseResponseDto> ResponseEntity<List<ResponseDto>> info() {
        return ResponseEntity.ok(getService().index().stream().map(e -> {
            ResponseDto response = (ResponseDto) new BaseResponseDto();
            response.bindEntity(e);
            return response;
        }).collect(Collectors.toList()));
    }
    
    @GetMapping("/{id}")
    public <ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> show(
        @PathVariable("id") Entity entity
    ) {
        ResponseDto response = (ResponseDto) new BaseResponseDto();
        response.bindEntity(entity);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ResponseDto> store(@RequestBody RequestDto request) {
        Entity entity = (Entity) new BaseEntity();
        log.info(entity.toString());
        log.info(request.toString());
        getConverter().convertDtoToEntity(request, entity);
        entity = (Entity) getService().save(entity);
        ResponseDto response = (ResponseDto) new BaseResponseDto();
        response.bindEntity(entity);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> update(
        @PathVariable("id") Entity entity,
        RequestDto request,
        RequestDto request2
    ) throws Exception {
        return null;
    }
    
    @DeleteMapping("/{id}")
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> delete(
        @PathVariable("id") Entity entity
    ) {
        return null;
    }
    
    protected Entity getEntity() {
        return null;
    }
    
    protected BaseService<Entity, BoardRepository> getService() {
        return null;
    }
    
    protected Converter getConverter() {
        return null;
    }
}
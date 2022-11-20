package tutoring.Project.base.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.board.repository.BoardRepository;
import tutoring.Project.util.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseController<Entity extends BaseEntity> {

    @GetMapping
    public <ResponseDto extends BaseResponseDto> ResponseEntity<List<ResponseDto>> info() {
        return ResponseEntity.ok(getService().index().stream().map(e -> {
            ResponseDto response = (ResponseDto) new BaseResponseDto();
            response.setData(e);
            return response;
        }).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public <ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> show(@PathVariable("id") Entity entity) {
        ResponseDto response = (ResponseDto) new BaseResponseDto();
        response.setData(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> store(RequestDto request) {
        Entity entity = (Entity) getService().save(getConverter().convertDtoToEntity(request, (Entity) new BaseEntity()));
        ResponseDto response = (ResponseDto) new BaseResponseDto();
        response.setData(entity);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> update(@PathVariable("id") Entity entity, RequestDto request) throws Exception {
        return null;
    }

    @DeleteMapping("/{id}")
    public <RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> delete(@PathVariable("id") Entity entity) {
        return null;
    }

    protected BaseService<Entity, BoardRepository> getService() {
        return null;
    }

    protected Converter getConverter() {
        return null;
    }
}

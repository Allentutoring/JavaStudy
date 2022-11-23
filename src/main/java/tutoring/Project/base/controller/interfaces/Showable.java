package tutoring.Project.base.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.base.entity.BaseEntity;

public interface Showable<Entity extends BaseEntity, RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> {

    @GetMapping("/{id}")
    default <ResponseDto extends BaseResponseDto> ResponseEntity<ResponseDto> show(
        @PathVariable("id") Entity entity) {
        ResponseDto response = (ResponseDto) new BaseResponseDto();
        ((ResponseDto) response).bindEntity(entity);
        return ResponseEntity.ok(response);
    }

}

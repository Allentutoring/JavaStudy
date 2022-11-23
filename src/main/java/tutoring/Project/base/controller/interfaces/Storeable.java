package tutoring.Project.base.controller.interfaces;

import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.base.entity.BaseEntity;

public interface Storeable<Entity extends BaseEntity, RequestDto extends BaseRequestDto, ResponseDto extends BaseResponseDto> {

}

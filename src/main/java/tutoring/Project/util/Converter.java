package tutoring.Project.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.entity.BaseEntity;

@Component
@RequiredArgsConstructor
public class Converter {

    private final ModelMapper modelMapper;

    public <Entity extends BaseEntity> void convertDtoToEntity(BaseRequestDto dto, Entity entity) {
        modelMapper.map(dto, entity);
    }

    public <Entity extends BaseEntity> Entity convertDtoToEntity(BaseRequestDto dto, Class<? extends Entity> entity) {
        return modelMapper.map(dto, entity);
    }

    public <Dto extends BaseRequestDto> Dto convertEntityToDto(Dto dto, BaseEntity entity) {
        modelMapper.map(entity, UserDto.class);
        return dto;
    }
}

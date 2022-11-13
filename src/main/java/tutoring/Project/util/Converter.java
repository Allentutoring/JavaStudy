package tutoring.Project.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.base.dto.BaseDto;
import tutoring.Project.base.entity.BaseEntity;

@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;

    public <Entity extends BaseEntity> Entity convertDtoToEntity(BaseDto dto, Entity entity) {
        modelMapper.map(dto, entity);
        return entity;
    }

    public <Dto extends BaseDto> Dto convertEntityToDto(Dto dto, BaseEntity entity) {
        modelMapper.map(entity, UserDto.class);
        return dto;
    }
}

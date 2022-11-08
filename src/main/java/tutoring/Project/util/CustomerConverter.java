package tutoring.Project.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tutoring.Project.auth.UserDto;
import tutoring.Project.base.BaseDto;
import tutoring.Project.base.BaseEntity;

@Component
@RequiredArgsConstructor
public class CustomerConverter {
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

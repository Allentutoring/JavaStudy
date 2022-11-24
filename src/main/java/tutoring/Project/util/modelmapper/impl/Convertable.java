package tutoring.Project.util.modelmapper.impl;

import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.base.entity.BaseEntity;

public interface Convertable {
    
    <Entity extends BaseEntity> void convertDtoToEntity(
        BaseRequestDto dto,
        Entity entity
    );
    
    <Entity extends BaseEntity> Entity convertDtoToEntity(
        BaseRequestDto dto,
        Class<? extends Entity> entity
    );
    
    <Dto extends BaseRequestDto> Dto convertEntityToDto(
        Dto dto,
        BaseEntity entity
    );
}

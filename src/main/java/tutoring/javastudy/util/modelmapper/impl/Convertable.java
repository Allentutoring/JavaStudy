package tutoring.javastudy.util.modelmapper.impl;

import tutoring.javastudy.base.dto.BaseRequestDto;
import tutoring.javastudy.base.entity.BaseEntity;

public interface Convertable {
    
    <Entity extends BaseEntity> void convertDtoToEntity(BaseRequestDto dto, Entity entity);
    
    <Entity extends BaseEntity> Entity convertDtoToEntity(
        BaseRequestDto dto, Class<? extends Entity> entity
    );
    
    <Dto extends BaseRequestDto> Dto convertEntityToDto(Dto dto, BaseEntity entity);
}

package tutoring.javastudy.util.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.stereotype.Component;
import tutoring.javastudy.auth.dto.UserDto;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.base.request.BaseRequestDto;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Component
public class Converter implements Convertable {
    
    private final ModelMapper modelMapper;
    
    public Converter(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration()
                   .setMethodAccessLevel(AccessLevel.PUBLIC)
                   .setFieldAccessLevel(AccessLevel.PRIVATE)
                   .setSkipNullEnabled(true)
                   .setFieldMatchingEnabled(true)
        // .setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection))
        ;
    }
    
    public <Entity extends BaseEntity> void convertDtoToEntity(
        BaseRequestDto dto, Entity entity
    )
    {
        modelMapper.map(dto, entity);
    }
    
    public <Entity extends BaseEntity> Entity convertDtoToEntity(
        BaseRequestDto dto, Class<? extends Entity> entity
    )
    {
        return modelMapper.map(dto, entity);
    }
    
    public <Dto extends BaseRequestDto> Dto convertEntityToDto(
        Dto dto, BaseEntity entity
    )
    {
        modelMapper.map(entity, UserDto.class);
        return dto;
    }
}

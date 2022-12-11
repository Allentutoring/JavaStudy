package tutoring.javastudy.base.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import tutoring.javastudy.base.dto.BaseDto;
import tutoring.javastudy.base.entity.BaseEntity;

@SuperBuilder
public abstract class BasePageResponseDto<Entity extends BaseEntity, ResponseDto extends BaseResponseDto<Entity>>
    extends BaseDto {
    
    @JsonProperty
    protected long total;
    @JsonProperty
    protected int totalPages;
    @JsonProperty
    protected int size;
    @JsonProperty
    protected int page;
    protected PageImpl<Entity> pageImpl;
    @JsonProperty
    protected List<ResponseDto> content;
    protected Sort sort;
    
    public BasePageResponseDto(PageImpl<Entity> pageImpl)
    {
        this.pageImpl = pageImpl;
        this.page = pageImpl.getPageable().getPageNumber();
        this.sort = pageImpl.getSort();
        this.totalPages = pageImpl.getTotalPages();
        this.total = pageImpl.getTotalElements();
        this.size = pageImpl.getSize();
        this.content = pageImpl.getContent().stream().map((this::bindEntity)).toList();
    }
    
    abstract public ResponseDto bindEntity(Entity entity);
}

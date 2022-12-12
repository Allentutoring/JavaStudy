package tutoring.javastudy.base.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;
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
    protected Page<Entity> pageImpl;
    @JsonProperty
    protected List<ResponseDto> content;
    protected Sort sort;
    
    public BasePageResponseDto(Page<Entity> page)
    {
        this.bindEntity(page);
    }
    
    public BasePageResponseDto(PageImpl<Entity> pageImpl)
    {
        this.bindEntity(pageImpl);
    }
    
    protected void bindEntity(Page<Entity> page)
    {
        this.pageImpl = page;
        this.page = page.getPageable()
                        .getPageNumber();
        this.sort = page.getSort();
        this.totalPages = page.getTotalPages();
        this.total = page.getTotalElements();
        this.size = page.getSize();
        this.content = page.getContent()
                           .stream()
                           .map((this::bindEntity))
                           .toList();
    }
    
    abstract public ResponseDto bindEntity(Entity entity);
}

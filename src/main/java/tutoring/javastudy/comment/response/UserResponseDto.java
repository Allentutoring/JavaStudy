package tutoring.javastudy.comment.response;

import lombok.Data;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.response.BaseResponseDto;

@Data
public class UserResponseDto extends BaseResponseDto<User> {
    
    protected long id;
    protected String email;
    protected String nickname;
    
    public UserResponseDto(User entity)
    {
        super(entity);
    }
    
    public void bindEntity(User entity)
    {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
    }
    
}

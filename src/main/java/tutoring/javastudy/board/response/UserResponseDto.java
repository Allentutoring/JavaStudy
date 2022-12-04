package tutoring.javastudy.board.response;

import lombok.Data;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.dto.BaseResponseDto;

@Data
public class UserResponseDto extends BaseResponseDto<User> {
    
    protected long id;
    protected String email;
    protected String nickname;
    
    public UserResponseDto(User entity)
    {
        super(entity);
    }
    
    @Override
    public void bindEntity(User entity)
    {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
    }
    
}

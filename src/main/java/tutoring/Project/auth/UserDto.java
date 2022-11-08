package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import tutoring.Project.base.BaseDto;

@Data
@AllArgsConstructor
@ToString
public class UserDto extends BaseDto {

    private String password;

    private String email;

    private String nickname;

}

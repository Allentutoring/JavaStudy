package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserDto {
    private String password;

    private String name;

    private String nickname;


}

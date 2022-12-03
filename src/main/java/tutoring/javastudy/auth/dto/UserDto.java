package tutoring.javastudy.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import tutoring.javastudy.base.dto.BaseRequestDto;

@Data
@AllArgsConstructor
@ToString
public class UserDto extends BaseRequestDto {

    private String email;

    private String password;

    private String nickname;

}

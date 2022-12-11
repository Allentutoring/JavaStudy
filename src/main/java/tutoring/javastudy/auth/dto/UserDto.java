package tutoring.javastudy.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import tutoring.javastudy.base.request.BaseRequestDto;

@Data
@AllArgsConstructor
@ToString
public class UserDto extends BaseRequestDto {
    
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email
    private String email;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String nickname;
    
}

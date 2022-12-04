package tutoring.javastudy.base.permission;

import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {
    
    @SneakyThrows
    @Override
    public boolean hasPermission(
        Authentication authentication, Object targetDomainObject, Object permission
    )
    {
        if ((authentication == null) || (targetDomainObject == null) ||
            !(permission instanceof String)) {
            return false;
        }
        
        return hasPrivilege(authentication,
                            targetDomainObject,
                            permission.toString().toUpperCase()
        );
    }
    
    @SneakyThrows
    @Override
    public boolean hasPermission(
        Authentication authentication, Serializable targetId, String targetType, Object permission
    )
    {
        if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication,
                            targetType.toUpperCase(),
                            permission.toString().toUpperCase()
        );
    }
    
    private boolean hasPrivilege(Authentication auth, Object targetType, String permission)
    {
        /*
         * targetType 을 string 으로 변환
         * ex) board -> BOARD
         * */
        String targetTypeOfString = targetType.toString().toUpperCase();
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            String authority = grantedAuth.getAuthority().toUpperCase();
            /*
             * targetType = board, permission = write 일 경우
             * BOARD_WRITE 인 privilege 가 존재하는지 확인
             * */
            if (authority.startsWith(targetTypeOfString) && authority.contains(permission)) {
                return true;
            }
        }
        return false;
    }
}

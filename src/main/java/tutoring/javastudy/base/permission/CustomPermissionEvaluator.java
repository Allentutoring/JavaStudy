package tutoring.javastudy.base.permission;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import tutoring.javastudy.base.permission.impl.Policy;

@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {
    
    private final Map<Class<?>, Policy> policies;
    
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
    throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        
        // targetTypeClass 가 BaseEntity type 의 class 일 경우 evaluators 에 custom evaluator 가 있는지 확인
        Class targetTypeClass = targetType.getClass();
        /*
         * targetTypeClass 가 BaseEntity 를 extends 했을 경우
         * why : evaluators 가 entity => evaluator 형식으로 저장하기 위해
         * BaseEntity 를 extends 한 Entity 만을 key 값으로 가지게 함
         * */
        /*if (BaseEntity.class.isAssignableFrom(targetTypeClass)) {
            if (this.evaluators.containsKey(targetTypeClass)) {
                // targetTypeClass 에 해당하는 evaluator 를 get 및 instance 를 생성하여 call hasPermission method
                PermissionEvaluator evaluator = this.evaluators.get(targetTypeClass)
                                                               .getDeclaredConstructor()
                                                               .newInstance();
                return evaluator.hasPermission(auth, targetType, permission);
            }
        }*/
        
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

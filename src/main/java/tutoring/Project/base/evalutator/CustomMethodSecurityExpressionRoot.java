package tutoring.Project.base.evalutator;

import java.security.Principal;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements
    MethodSecurityExpressionOperations {
    
    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }
    
    public boolean isMember(Long OrganizationId) {
        Principal principal = (Principal) this.getPrincipal();
        return principal.getName().equals("user2@email.com");
        // return user.getOrganization().getId().longValue() == OrganizationId.longValue();
    }
    
    @Override
    public void setFilterObject(Object filterObject) {
    
    }
    
    @Override
    public Object getFilterObject() {
        return null;
    }
    
    @Override
    public void setReturnObject(Object returnObject) {
    
    }
    
    @Override
    public Object getReturnObject() {
        return null;
    }
    
    @Override
    public Object getThis() {
        return null;
    }
}


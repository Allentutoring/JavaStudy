package tutoring.javastudy.base.permission;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
    implements MethodSecurityExpressionOperations {
    
    private HttpServletRequest request;
    private Object filterObject;
    private Object returnObject;
    private Object target;
    
    public CustomMethodSecurityExpressionRoot(Authentication authentication)
    {
        super(authentication);
    }
    
    public boolean isAdmin(Long OrganizationId)
    {
        Principal principal = (Principal) this.getPrincipal();
        return principal.getName().equals("user@email.com");
    }
    
    @Override
    public Object getFilterObject()
    {
        return this.filterObject;
    }
    
    @Override
    public void setFilterObject(Object filterObject)
    {
        this.filterObject = filterObject;
    }
    
    @Override
    public Object getReturnObject()
    {
        return this.returnObject;
    }
    
    @Override
    public void setReturnObject(Object returnObject)
    {
        this.returnObject = returnObject;
    }
    
    @Override
    public Object getThis()
    {
        return target;
    }
}


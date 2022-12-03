package tutoring.javastudy.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/*
 * To use the above-defined Spring Security configuration, we need to attach it to the web application.
 * We'll use the WebApplicationInitializer, so we don't need to provide any web.xml:
 *
 * Note that this initializer isn't necessary if we're using a Spring Boot application.
 * For more details on how the security configuration is loaded in Spring Boot, have a look at our article on Spring Boot security auto-configuration.
 * */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(SecurityConfig.class);

        servletContext.addListener(new ContextLoaderListener(root));

        servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
    }

}
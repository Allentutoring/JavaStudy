package tutoring.javastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JavaStudyApplication {
    
    public static void main(String[] args)
    {
        SpringApplication.run(JavaStudyApplication.class, args);
    }
    
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}

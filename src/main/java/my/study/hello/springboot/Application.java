package my.study.hello.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
   Main class of the project
 */
//@EnableJpaAuditing // activate JPA Auditing // delete
@SpringBootApplication // auto setting for springboot, beans, configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //run WAS
    }
}

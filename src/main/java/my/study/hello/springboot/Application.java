package my.study.hello.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
   Main class of the project
 */
@SpringBootApplication //auto setting for springboot, beans, configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //run WAS
    }
}

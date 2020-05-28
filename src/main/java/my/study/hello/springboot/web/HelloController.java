package my.study.hello.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //make controller which returns JSON data
public class HelloController {

    @GetMapping("/hello") //make API which can response a GET request of HTTP method
    public String hello() {
        return "hello~";
    }
}

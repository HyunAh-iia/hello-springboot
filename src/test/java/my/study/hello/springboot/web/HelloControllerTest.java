package my.study.hello.springboot.web;

import my.study.hello.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // Run other runner instead of Junit's runner when testing, connect springboot test and Junit.
@WebMvcTest(controllers = HelloController.class)
// annotation focus on Web(Spring MVC) - you can use @Controller, @ControllerAdvice, but not @Service, @Component, @Repository
public class HelloControllerTest {

    @Autowired // injected with beans from spring
    private MockMvc mvc; // start point to test spring MVC, using for testing web API(HTTP GET, POST...)

    @Test
    public void hello_ReturnHello() throws Exception {
        String hello = "hello~";

        mvc.perform(get("/hello")) // request GET method
                .andExpect((status().isOk())) // HTTP Header Status - 200
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_ReturnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1010;

        mvc.perform(get("/hello/dto")
                .param("name", name) // @RequestParam("param") : means to get param from requests
                .param("amount", String.valueOf(amount))) // .param : only String type, should change type int, date to String
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.amount", is(amount)));
    }
}

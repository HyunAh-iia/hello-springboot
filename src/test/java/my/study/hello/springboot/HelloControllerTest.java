package my.study.hello.springboot;

import my.study.hello.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // Run other constructor instead of Junit's constructor when testing
@WebMvcTest(controllers = HelloController.class)
// annotation focus on Web(Spring MVC) - you can use @Controller, @ControllerAdvice, but not @Service, @Component, @Repository
public class HelloControllerTest {

    @Autowired // injected with beans from spring
    private MockMvc mvc; // start point to test spring MVC, using for testing web API(HTTP GET, POST...)

    @Test
    public void hello_ReturnHello() throws Exception {
        String hello = "hello~";

        mvc.perform(get("/hello"))
                .andExpect((status().isOk()))
                .andExpect(content().string(hello));
    }
}

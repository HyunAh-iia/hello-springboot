package my.study.hello.springboot.web;

import my.study.hello.springboot.config.auth.SecurityConfig;
import my.study.hello.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
// Run other runner instead of Junit's runner when testing, connect springboot test and Junit.
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
// annotation focus on Web(Spring MVC) - you can use @Controller, @ControllerAdvice, but not @Service, @Component, @Repository
public class HelloControllerTest {

    @Autowired // injected with beans from spring.
    private MockMvc mvc; // start point to test spring MVC, using for testing web API(HTTP GET, POST...)

    @WithMockUser(roles = "USER")
    @Test
    public void hello_ReturnHello() throws Exception {
        String hello = "hello~";

        mvc.perform(get("/hello")) // request GET method
                .andExpect((status().isOk())) // HTTP Header Status - 200
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
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

/* [Note] @WebMvcTest reads (@ControllerAdvice, @Controller)
 * @WebMvcTest doesn't scan (@Repository, @Service, @Component) so it occur error
 *      : org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'my.study.hello.springboot.config.auth.CustomOAuth2UserService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
 * => @WebMvcTest can read SecurityConfig, but can't read CustomOAuth2UserService that init. to SecurityConfig.
 * Therefore, I need to remove SecurityConfig in scan list.
 */

/* [Note] error : java.lang.IllegalArgumentException: JPA metamodel must not be empty!
 * The error occur by @EnableJpaAuditing
 * to use @EnableJpaAuditing, there should be one more @Entity class.
 * So, in Application.java, 하아..
 * @EnableJpaAuditing과 @SpringBootApplication을 분리해야함.
 */

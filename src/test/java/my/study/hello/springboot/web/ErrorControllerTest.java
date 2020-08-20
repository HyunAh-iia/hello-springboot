package my.study.hello.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import my.study.hello.springboot.exception.ApiError;
import my.study.hello.springboot.web.dto.PostsSaveRequestDto;
import my.study.hello.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void whenException_thenCatch() throws Exception {
        // given
        String subUrl = "/error/exception";
        String url = "http://localhost:" + port + subUrl;

        // when
        MvcResult result = mvc.perform(get(url))
                .andExpect(status().isInternalServerError())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isNotEmpty();
        ObjectMapper mapper = new ObjectMapper();

        ApiError error = mapper.readValue(content, ApiError.class);
        log.error(mapper.writeValueAsString(error));

        assertThat(error.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(error.getMessage()).isNull();
    }

    @Test
    public void whenExceptionWith_thenCatch() throws Exception {
        // given
        String subUrl = "/error/exception-message";
        String url = "http://localhost:" + port + subUrl;

        // when
        MvcResult result = mvc.perform(get(url))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isNotEmpty();
        ObjectMapper mapper = new ObjectMapper();

        ApiError error = mapper.readValue(content, ApiError.class);
        log.error(mapper.writeValueAsString(error));

        assertThat(error.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(error.getMessage()).isNotNull();
    }

    @Test
    public void whenMethodArgumentNotValid_thenCatch() throws Exception {
        // given
        String subUrl = "/error/illegal-argument";
        String url = "http://localhost:" + port + subUrl;

        // when
        MvcResult result = mvc.perform(get(url))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isNotEmpty();

        ObjectMapper mapper = new ObjectMapper();
        ApiError error = mapper.readValue(content, ApiError.class);
        log.error(mapper.writeValueAsString(error));

        assertThat(error.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(error.getMessage()).isNotNull();
    }
}
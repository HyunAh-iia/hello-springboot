package my.study.hello.springboot.web;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
@NoArgsConstructor
@RestController
public class ErrorController {

    @GetMapping("/error/exception")
    public void throwException() throws Exception {
        log.error("!@@ throwException");
        throw new Exception();
    }

    @GetMapping("/error/exception-message")
    public void throwExceptionWithMessage() throws Exception {
        throw new Exception("메시지를 함께 넘겼다.");
    }

    @GetMapping("/error/illegal-argument")
    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException("메시지도 함께 넘김");
    }
}

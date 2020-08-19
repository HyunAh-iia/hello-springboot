package my.study.hello.springboot.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApiError {

    private String type;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(String type, HttpStatus status, String message, List<String> errors) {
        super();
        this.type = type;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(String type, HttpStatus status, String message, String error) {
        super();
        this.type = type;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
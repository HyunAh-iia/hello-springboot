package my.study.hello.springboot.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import my.study.hello.springboot.utils.JsonUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // 400
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        // BindException: This exception is thrown when fatal binding errors occur.

        String classType = ex.getClass().getSimpleName();
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError(classType, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        logApiError(apiError, ex);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        // MethodArgumentNotValidException: This exception is thrown when argument annotated with @Valid failed validation:

        String classType = ex.getClass().getSimpleName();
        final List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(classType, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        logApiError(apiError, ex);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        // MissingServletRequestParameterException: This exception is thrown when request missing parameter:

        String classType = ex.getClass().getSimpleName();
        String error = ex.getParameterName() + " parameter is missing";

        ApiError apiError = new ApiError(classType, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

        logApiError(apiError, ex);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }


    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        //ConstrainViolationException: This exception reports the result of constraint violations:

        String classType = ex.getClass().getSimpleName();
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError = new ApiError(classType, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        logApiError(apiError, ex);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        // MethodArgumentTypeMismatchException: This exception is thrown when method argument is not the expected type:

        String classType = ex.getClass().getSimpleName();
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError = new ApiError(classType, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

        logApiError(apiError, ex);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        final String classType = ex.getClass().getSimpleName();
        final ApiError apiError = new ApiError(classType, HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");

        log.error("!@@ exception", ex);
        logApiError(apiError, ex);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private ApiError logApiError(ApiError apiError, Exception ex) {
        try {
            log.error(JsonUtils.writeValueAsPrettyString(apiError+"2"));
        } catch (JsonProcessingException e) {

            log.error("!@@ exception", ex);
            log.error("!@@ JsonProcessingException - apiError response");
        }

        return apiError;
    }
}

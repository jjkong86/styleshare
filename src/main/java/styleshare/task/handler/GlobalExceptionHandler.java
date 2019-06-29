package styleshare.task.handler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.h2.jdbc.JdbcSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import lombok.extern.slf4j.Slf4j;
import styleshare.task.exception.JsonParseException;
import styleshare.task.exception.ValidCustomException;
import styleshare.task.response.ExceptionResponse;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MismatchedInputException.class)
    public ExceptionResponse mismatchException(MismatchedInputException exception) {
        return ExceptionResponse.builder().code(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).build();
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ExceptionResponse jsonParseException(JsonParseException exception) {
        return ExceptionResponse.builder().code(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).build();
    }

    @ExceptionHandler(value = JdbcSQLException.class)
    public ExceptionResponse jdbcSQLException(JdbcSQLException exception) {
        return ExceptionResponse.builder().code(5001).error(exception.getMessage()).build();
    }

    @ExceptionHandler(value = SQLException.class)
    public ExceptionResponse sqlException(SQLException exception) {
        return ExceptionResponse.builder().code(50000).error(exception.getMessage()).build();
    }

    @ExceptionHandler(value = BindException.class)
    public ExceptionResponse bindException(BindException exception) {
        return ExceptionResponse.builder().code(HttpStatus.BAD_REQUEST.value()).error("Mismatched json format").build();
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ExceptionResponse noSuchElementException(NoSuchElementException exception) {
        return ExceptionResponse.builder().code(HttpStatus.NO_CONTENT.value()).error(exception.getMessage()).build();
    }

    @ExceptionHandler(value = ValidCustomException.class)
    public ExceptionResponse validCustomException(ValidCustomException exception) {
        return ExceptionResponse.builder().code(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    public ExceptionResponse exception(Exception exception) {
        log.info("exception={}", exception);
        return ExceptionResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(exception.getMessage()).build();
    }
}

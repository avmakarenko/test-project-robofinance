package ru.robofinance.test.project.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.robofinance.test.project.exception.CustomerNotFindException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class WebMvcExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerNotFindException.class})
    public void handleCustomerException(HttpServletResponse httpServletResponse, Throwable ex) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }

}
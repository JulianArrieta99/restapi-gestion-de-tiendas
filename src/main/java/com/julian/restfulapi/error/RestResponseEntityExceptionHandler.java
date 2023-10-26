package com.julian.restfulapi.error;

import com.julian.restfulapi.error.dto.CustomError;
import com.julian.restfulapi.error.dto.CustomErrorBadRequest;
import com.julian.restfulapi.error.dto.ErrorMessage;
import com.julian.restfulapi.error.local.StoreNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public RestResponseEntityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> localNotFoundException(StoreNotFoundException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorBadRequest error = new CustomErrorBadRequest();
        error.setErrorCode(400);
        error.setErrorMessage(messageSource.getMessage("error.bad_request.message", null, LocaleContextHolder.getLocale()));
        error.setTimestamp(LocalDateTime.now());
        error.setTrace(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomError error = new CustomError();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(messageSource.getMessage("error.validation.message", null, LocaleContextHolder.getLocale()));

        // Obtén la lista de errores de validación individual
        List<String> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        error.setDetails(validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    
}

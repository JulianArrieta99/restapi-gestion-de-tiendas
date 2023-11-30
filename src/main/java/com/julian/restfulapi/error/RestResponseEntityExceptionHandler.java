package com.julian.restfulapi.error;

import com.julian.restfulapi.error.dto.ApiError;
import com.julian.restfulapi.error.local.CustomerNotFoundException;
import com.julian.restfulapi.error.local.ManagerNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    public RestResponseEntityExceptionHandler() {

    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return ex.getMessage();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no se pudo leer correctamente", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Los datos no son válidos");
        apiError.setErrors(errors);
        apiError.setTimestamp(LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error de integridad de datos", "Ya existe una entidad con el id especificado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<Object> handleRuntimeException(ManagerNotFoundException ex) {
        // Crear un objeto de respuesta personalizado
        ApiError apiError = new ApiError();
        apiError.setMessage("Error al procesar la solicitud");
        apiError.setStatus(HttpStatus.NOT_FOUND);

        // Devolver la respuesta personalizada y el código de estado adecuado
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}

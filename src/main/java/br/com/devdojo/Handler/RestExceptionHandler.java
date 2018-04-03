package br.com.devdojo.Handler;

import br.com.devdojo.Error.ResourceNotFoundDetails;
import br.com.devdojo.Error.ResourceNotFoundException;
import br.com.devdojo.Error.ValidationErroDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfn){
        ResourceNotFoundDetails rfnDetails =  ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getDate())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found")
                .detail(rfn.getMessage())
                .developerMessage(rfn.getClass().getName())
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException){
        List<FieldError> lista =  manvException.getBindingResult().getFieldErrors();
        String field = lista.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = lista.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErroDetails rfnDetails =  ValidationErroDetails.Builder
                .newBuilder()
                .timestamp(new Date().getDate())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error")
                .detail("Field Validation Error")
                .developerMessage(manvException.getClass().getName())
                .field(field)
                .fieldMessage(fieldMessages)
                .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }
}

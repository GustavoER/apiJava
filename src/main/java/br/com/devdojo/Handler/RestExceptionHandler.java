package br.com.devdojo.Handler;

import br.com.devdojo.Error.ResourceNotFoundDetails;
import br.com.devdojo.Error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
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
}

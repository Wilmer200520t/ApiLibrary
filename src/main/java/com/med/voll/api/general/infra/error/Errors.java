package com.med.voll.api.general.infra.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Errors {

    @ExceptionHandler(EntityNotFoundException.class) //cuando se encuentra este error ejecuta la funcion
    public ResponseEntity<?> Error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> Error400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(DataError400::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> ValidationExceptionErr(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DataError400(String field , String message){
        public DataError400(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }
}


package com.example.PrimeiroProjetoSpring.Exception;

import com.example.PrimeiroProjetoSpring.Exception.customExceptions.CategoryWithProductsException;
import java.util.HashMap;
import java.util.Map;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.ObjectNotFoundException;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.ObjectAlreadyExistsException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validationExceptionHandler(MethodArgumentNotValidException ex){
        
        //HashMap conténdo pares: campo com erro, mensagem do erro
        Map<String, Object> errors = new HashMap<>();
        
        errors.put("status", "400");
        errors.put("timestamp", LocalDateTime.now().withNano(0));
        errors.put("error", "1 ou mais campos inválidos");
        
        //selecionando todos os campos com erros da exceção e inserindo no HashMap "errors"
        ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.put(error.getField(), error.getDefaultMessage());            
        });        
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> objectNotFoundException (ObjectNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", ex.getMessage()));

    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> objectAlreadyExistsException (ObjectAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("400", ex.getMessage()));
    }
    
    @ExceptionHandler(CategoryWithProductsException.class)
    public ResponseEntity<ErrorResponse> categoryWithProductsException (CategoryWithProductsException ex){
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("400", ex.getMessage()));
    } 
}

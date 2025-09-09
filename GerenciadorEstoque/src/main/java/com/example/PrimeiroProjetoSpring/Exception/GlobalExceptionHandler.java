
package com.example.PrimeiroProjetoSpring.Exception;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.CategoryNotFoundException;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.ObjectAlreadyExistsException;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //método utilitário para gerar Maps de resposta padronizados
    private Map<String, String> mapGenerator(String statusNumber, String message){

        Map<String, String> errors = new HashMap<>();
        errors.put("status", statusNumber);
        errors.put("error", message);
        errors.put("timestamp", LocalTime.now().withNano(0).toString());

        return errors;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException ex){
        
        //HashMap conténdo pares: campo com erro, mensagem do erro
        Map<String, String> errors = mapGenerator("400", "1 ou mais campos inválidos");
        
        //selecionando todos os campos com erros da exceção e inserindo no HashMap "errors"
        ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.put(error.getField(), error.getDefaultMessage());            
        });        
        
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> categoryNotFoundExceptionHandler (CategoryNotFoundException ex){

        Map<String, String> errors = mapGenerator("404", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);

    }

    @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<Map<String, String>> productNotFoundExceptionHandler (ProductNotFoundException ex){

        Map<String, String> errors = mapGenerator("404", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);

    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<Map<String, String >>objectAlreadyExistsException (ObjectAlreadyExistsException ex) {

        Map<String, String> errors = mapGenerator("400", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

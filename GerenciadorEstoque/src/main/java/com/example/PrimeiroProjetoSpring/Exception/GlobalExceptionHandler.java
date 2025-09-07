
package com.example.PrimeiroProjetoSpring.Exception;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException ex){
        
        //HashMap conténdo pares: campo com erro, mensagem do erro
        Map<String, String> errors = new HashMap<>();     
        
        errors.put("status", "400");
        errors.put("timestamp", LocalTime.now().withNano(0).toString());
        
        //selecionando todos os campos com erros da exceção e inserindo no HashMap "errors"
        ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.put(error.getField(), error.getDefaultMessage());            
        });        
        
        return ResponseEntity.badRequest().body(errors);
    }                
    
}

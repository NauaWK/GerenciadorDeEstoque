
package com.example.GerenciadorEstoque.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    
    private final String status;
    private final String error;
    private final LocalDateTime timestamp;
    
    public ErrorResponse(String status, String error){
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now().withNano(0);
    }    
    
    public String getStatus(){
        return status;
    }
    
    public String getError(){
        return error;
    }
    
    public LocalDateTime getTimeStamp(){
        return timestamp;
    }
    
}


package com.example.GerenciadorEstoque.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    
    private String status;
    private String error;
    private LocalDateTime timestamp;
    
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

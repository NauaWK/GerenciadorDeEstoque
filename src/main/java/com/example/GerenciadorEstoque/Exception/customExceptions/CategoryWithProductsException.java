
package com.example.GerenciadorEstoque.Exception.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryWithProductsException extends RuntimeException {
    public CategoryWithProductsException(String message) {
        super(message);
    }
}


package com.example.GerenciadorEstoque.Exception.customExceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}

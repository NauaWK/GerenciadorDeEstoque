
package com.example.GerenciadorEstoque.Exception.customExceptions;

public class CategoryWithProductsException extends RuntimeException {
    public CategoryWithProductsException(String message) {
        super(message);
    }
}

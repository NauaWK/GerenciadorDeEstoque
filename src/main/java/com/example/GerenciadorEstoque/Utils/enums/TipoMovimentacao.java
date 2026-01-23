
package com.example.GerenciadorEstoque.utils.enums;

import com.example.GerenciadorEstoque.exception.customExceptions.InvalidEnumException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoMovimentacao {
    
    VALOR, QUANTIDADE, NOME;
    
    //tentativa de conversão de string do JSON para enum, permitindo maior flexibilidade na sintaxe
    @JsonCreator
    public static TipoMovimentacao from(String movimentacao){
        try {
            return TipoMovimentacao.valueOf(movimentacao.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Tipo de movimentação inválida!");
        }                       
    }  
    
}

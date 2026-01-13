
package com.example.GerenciadorEstoque.DTO.ProdutoDTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        
        Long id, 
        String nome, 
        BigDecimal preco, 
        int quantidade, 
        LocalDateTime dataAdicao, 
        LocalDateTime dataModificacao, 
        Long categoriaId ){}

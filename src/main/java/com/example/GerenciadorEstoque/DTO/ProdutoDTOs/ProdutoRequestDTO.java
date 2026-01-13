
package com.example.GerenciadorEstoque.DTO.ProdutoDTOs;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Range;


public record ProdutoRequestDTO(
        
    @NotBlank(message = "Nome do produto é obrigatório")
    String nome,
    
    @Positive(message = "O preço deve ser maior que 0")
    @DecimalMax(value = "10000.00", message = "O preço não pode ultrapassar R$10.000,00")
    BigDecimal preco,
    
    @Range(min = 1, max = 100, message = "A quantidade de produtos deve estar entre 1 e 100")
    int quantidade,
    
    @NotNull
    Long categoriaId

    ){}

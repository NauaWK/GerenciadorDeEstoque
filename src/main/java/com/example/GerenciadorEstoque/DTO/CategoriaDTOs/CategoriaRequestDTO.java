
package com.example.GerenciadorEstoque.dto.CategoriaDTOs;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        
    @NotBlank(message = "Nome da categoria é obrigatório")
    String nome
        
    ){}

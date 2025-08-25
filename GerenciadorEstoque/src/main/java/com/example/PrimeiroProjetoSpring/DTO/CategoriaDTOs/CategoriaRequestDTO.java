
package com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        
    @NotBlank(message = "Nome da categoria é obrigatório")
    String nome, 
    String descricao
        
    ){}

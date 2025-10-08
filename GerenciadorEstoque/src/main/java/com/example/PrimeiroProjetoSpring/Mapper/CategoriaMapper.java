
package com.example.PrimeiroProjetoSpring.Mapper;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    
    public Categoria convertDtoToCategoria(CategoriaRequestDTO categoriaRequestDTO){
        return new Categoria(
            categoriaRequestDTO.nome()
        );      
    }  
    
    public CategoriaResponseDTO convertCategoriaToDto(Categoria categoria){
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(),
            categoria.getQuantidade()
        );
    }        
}

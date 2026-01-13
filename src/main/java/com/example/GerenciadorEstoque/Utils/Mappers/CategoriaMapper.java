
package com.example.GerenciadorEstoque.Utils.Mappers;

import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.Model.Categoria;
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

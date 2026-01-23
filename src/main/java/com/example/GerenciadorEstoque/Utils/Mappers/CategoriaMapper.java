
package com.example.GerenciadorEstoque.utils.mappers;

import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.entities.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    
    public Categoria toCategoria(CategoriaRequestDTO categoriaRequestDTO){
        return new Categoria(
            categoriaRequestDTO.nome()
        );      
    }  
    
    public CategoriaResponseDTO toDto(Categoria categoria){
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(),
            categoria.getQuantidade()
        );
    }        
}

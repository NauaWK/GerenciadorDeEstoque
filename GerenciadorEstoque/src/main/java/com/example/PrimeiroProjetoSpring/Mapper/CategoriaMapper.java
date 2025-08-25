
package com.example.PrimeiroProjetoSpring.Mapper;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    
     //conversão de CategoriaRequestDTO para Categoria
<<<<<<< HEAD:GerenciadorEstoque/src/main/java/com/example/PrimeiroProjetoSpring/Mapper/CategoriaMapper.java
    public Categoria convertDtoToCategoria(CategoriaRequestDTO categoriaRequestDTO){
=======
    public Categoria convertDtoToModel(CategoriaRequestDTO categoriaRequestDTO){
>>>>>>> bcaee6bb845453711d98b7e30de5716bc693e9b2:PrimeiroProjetoSpring/src/main/java/com/example/PrimeiroProjetoSpring/Mapper/CategoriaMapper.java
        return new Categoria(
            categoriaRequestDTO.nome(), 
            categoriaRequestDTO.descricao()
        );      
    }  
    
    //conversão de Categoria para CategoraRequestDTO
    public CategoriaResponseDTO convertCategoriaToDTO(Categoria categoria){
        return new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(), 
            categoria.getDescricao()
        );
    }    
    
}

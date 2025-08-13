
package com.example.PrimeiroProjetoSpring.Mapper;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    
    //conversão de ProdutoRequestDTO para Produto
    public Produto convertDtoToModel(ProdutoRequestDTO produtoRequestDTO){
        return new Produto(
            produtoRequestDTO.nome(), 
            produtoRequestDTO.preco(), 
            produtoRequestDTO.quantidade(),
            produtoRequestDTO.categoriaId()
        );      
    }  
    
    //conversão de Produto para ProdutoResponseDTO
    public ProdutoResponseDTO convertProdutoToDTO(Produto produto){
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(), 
                produto.getPreco(), 
                produto.getQuantidade(),
                produto.getDataAdicao(),
                produto.getDataModificacao()
        );      
    }    
}

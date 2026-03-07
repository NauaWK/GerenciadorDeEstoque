
package com.example.GerenciadorEstoque.utils.Mappers;

import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoRequestDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.entity.Categoria;
import com.example.GerenciadorEstoque.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    
    public Produto toProduto(ProdutoRequestDTO produtoRequestDTO, Categoria categoria){
        return new Produto(
            produtoRequestDTO.nome(), 
            produtoRequestDTO.preco(), 
            produtoRequestDTO.quantidade(),
            categoria
        );      
    }  
    
    public ProdutoResponseDTO toDto(Produto produto){
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(), 
                produto.getPreco(), 
                produto.getQuantidade(),
                produto.getDataAdicao(),
                produto.getDataModificacao(),
                produto.getCategoria().getId()
        );      
    }    
}


package com.example.PrimeiroProjetoSpring.Mapper;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    
    public Produto convertDtoToProduto(ProdutoRequestDTO produtoRequestDTO, Categoria categoria){
        return new Produto(
            produtoRequestDTO.nome(), 
            produtoRequestDTO.preco(), 
            produtoRequestDTO.quantidade(),
            categoria
        );      
    }  
    
    public ProdutoResponseDTO convertProdutoToDTO(Produto produto){
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

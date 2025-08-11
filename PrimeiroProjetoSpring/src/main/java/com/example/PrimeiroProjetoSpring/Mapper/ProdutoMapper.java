
package com.example.PrimeiroProjetoSpring.Mapper;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    
    //conversão de ProdutoRequestDTO para Produto
    public Produto convertDtoToModel(ProdutoRequestDTO produtoRequestDTO){
        Produto produtoConvertido = new Produto(
                produtoRequestDTO.getNome(), 
                produtoRequestDTO.getPreco(), 
                produtoRequestDTO.getQuantidade()               
        );      
        return produtoConvertido;
    }  
    
    //conversão de Produto para ProdutoResponseDTO
    public ProdutoResponseDTO convertProdutoToDTO(Produto produtoModel){
        ProdutoResponseDTO produtoConvertido = new ProdutoResponseDTO(
                produtoModel.getNome(), 
                produtoModel.getPreco(), 
                produtoModel.getQuantidade(),
                produtoModel.getDataAdicao(),
                produtoModel.getDataModificacao()
        );      
        return produtoConvertido;
    }    
}

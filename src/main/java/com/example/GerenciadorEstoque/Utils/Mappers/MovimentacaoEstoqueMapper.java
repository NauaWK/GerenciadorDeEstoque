
package com.example.GerenciadorEstoque.utils.Mappers;

import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.GerenciadorEstoque.entity.MovimentacaoEstoque;
import com.example.GerenciadorEstoque.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoEstoqueMapper {
    
    public MovimentacaoEstoque toMovimentacao(Produto produto, MovimentacaoRequestDTO movimentacaoRequest){
        return new MovimentacaoEstoque(
            produto,
            movimentacaoRequest.tipo(), 
            movimentacaoRequest.valorAlterado(),
            movimentacaoRequest.quantidadeAlterada(),
            movimentacaoRequest.nomeAlterado(),
            movimentacaoRequest.observacao()
        );      
    }  
    
    public MovimentacaoResponseDTO toDto (MovimentacaoEstoque MovimentacaoEstoque){
        return new MovimentacaoResponseDTO(
            MovimentacaoEstoque.getId(),
            MovimentacaoEstoque.getProduto().getId(),
            MovimentacaoEstoque.getDataMovimentacao(),
            MovimentacaoEstoque.getTipo(), 
            MovimentacaoEstoque.getValorAlterado(),
            MovimentacaoEstoque.getQuantidadeAlterada(),
            MovimentacaoEstoque.getNomeAlterado(),
            MovimentacaoEstoque.getObservacao()
        );      
    }    
    
}

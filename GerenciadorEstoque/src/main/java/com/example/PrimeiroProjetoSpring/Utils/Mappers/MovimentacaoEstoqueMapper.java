
package com.example.PrimeiroProjetoSpring.Utils.Mappers;

import com.example.PrimeiroProjetoSpring.DTO.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.MovimentacaoEstoque;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoEstoqueMapper {
    
    public MovimentacaoEstoque convertDtoToMovimentacao(Produto produto, MovimentacaoRequestDTO movimentacaoRequest){
        return new MovimentacaoEstoque(
            produto,
            movimentacaoRequest.tipo(), 
            movimentacaoRequest.valorAlterado(),
            movimentacaoRequest.quantidadeAlterada(),
            movimentacaoRequest.nomeAlterado(),
            movimentacaoRequest.obervacao()
        );      
    }  
    
    public MovimentacaoResponseDTO convertMovimentacaoToDto (MovimentacaoEstoque MovimentacaoEstoque){
        return new MovimentacaoResponseDTO(
            MovimentacaoEstoque.getId(),
            MovimentacaoEstoque.getProdutoId(),
            MovimentacaoEstoque.getDataMovimentacao(),
            MovimentacaoEstoque.getTipo(), 
            MovimentacaoEstoque.getValorAlterado(),
            MovimentacaoEstoque.getQuantidadeAlterada(),
            MovimentacaoEstoque.getNomeAlterado(),
            MovimentacaoEstoque.getObservacao()
        );      
    }    
    
}

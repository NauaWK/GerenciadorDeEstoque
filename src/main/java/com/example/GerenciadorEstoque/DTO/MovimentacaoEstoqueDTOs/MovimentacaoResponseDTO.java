
package com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs;

import com.example.GerenciadorEstoque.utils.enums.TipoMovimentacao;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimentacaoResponseDTO(
        
        Long id,
        Long produtoId,
        LocalDateTime data_movimentacao,
        TipoMovimentacao tipo,
        BigDecimal valorAlterado,
        int quantidadeAlterada,
        String nomeAlterado,
        String obervacao
              
        ){}

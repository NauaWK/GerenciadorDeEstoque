
package com.example.PrimeiroProjetoSpring.DTO.MovimentacaoEstoqueDTOs;

import com.example.PrimeiroProjetoSpring.Utils.Enum.TipoMovimentacao;
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

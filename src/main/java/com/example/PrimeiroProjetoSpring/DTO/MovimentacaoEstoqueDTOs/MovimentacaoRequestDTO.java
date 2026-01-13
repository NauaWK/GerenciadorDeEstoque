
package com.example.PrimeiroProjetoSpring.DTO.MovimentacaoEstoqueDTOs;

import com.example.PrimeiroProjetoSpring.Utils.Enum.TipoMovimentacao;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Range;

public record MovimentacaoRequestDTO(
        
        @NotNull
        Long produtoId,

        @NotNull
        TipoMovimentacao tipo,

        @Positive(message = "O preço deve ser maior que 0")
        @DecimalMax(value = "10000.00", message = "O preço não pode ultrapassar R$10.000,00")
        BigDecimal valorAlterado,

        @Range(min = 1, max = 100, message = "A quantidade de produtos deve estar entre 1 e 100")
        int quantidadeAlterada,

        String nomeAlterado,

        String obervacao
             
        ){}

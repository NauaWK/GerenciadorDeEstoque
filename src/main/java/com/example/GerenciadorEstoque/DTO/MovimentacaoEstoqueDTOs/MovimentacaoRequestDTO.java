
package com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs;

import com.example.GerenciadorEstoque.utils.enums.TipoMovimentacao;
import jakarta.validation.constraints.AssertTrue;
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

    @Range(max = 300, message = "A quantidade máxima de produtos deve ser menor ou igual a 300")
    int quantidadeAlterada,

    String nomeAlterado,

    String observacao){
        
        @AssertTrue(message = "nomeAlterado deve ser informado quando tipoMovimentacao = NOME") 
        public boolean isNomeValido() {
            return tipo != TipoMovimentacao.NOME || nomeAlterado != null;
        }
        
        @AssertTrue(message = "valorAlterado deve ser informado quando tipoMovimentacao = VALOR") 
        public boolean isValorValido() {
            return tipo != TipoMovimentacao.VALOR || valorAlterado != null;
        }
        
        @AssertTrue(message = "quantidadeAlterada deve ser maior ou igual a 1 quando tipoMovimentacao = QUANTIDADE") 
        public boolean isQtdValida() {
            return tipo != TipoMovimentacao.QUANTIDADE || quantidadeAlterada >= 1;
        }
        
    }

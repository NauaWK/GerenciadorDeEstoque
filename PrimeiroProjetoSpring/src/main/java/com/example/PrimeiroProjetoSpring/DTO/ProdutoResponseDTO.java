
package com.example.PrimeiroProjetoSpring.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(String nome, BigDecimal preco, int quantidade, LocalDateTime dataAdicao, LocalDateTime dataModificacao) {
    
}

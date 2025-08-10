
package com.example.PrimeiroProjetoSpring.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Range;


public class ProdutoRequestDTO {
    
    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;
    
    @Positive
    @DecimalMax(value = "10000.00", message = "O preço não pode ultrapassar R$10.000,00")
    private BigDecimal preco;
    
    @Range(min = 1, max = 100, message = "A quantidade de produtos deve estar entre 1 e 999")
    private int quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
     
}

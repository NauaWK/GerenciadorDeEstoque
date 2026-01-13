
package com.example.GerenciadorEstoque.Model;

import com.example.GerenciadorEstoque.Utils.Enum.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_movimentacoes")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private LocalDateTime dataMovimentacao;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    //campos adicionais
    private BigDecimal valorAlterado; //se tipo for VALOR
    private Integer quantidadeAlterada; //se tipo for QUANTIDADE
    private String nomeAlterado; //se tipo for NOME

    public MovimentacaoEstoque() {}

    public MovimentacaoEstoque(Produto produto, TipoMovimentacao tipo, BigDecimal valorAlterado, Integer quantidadeAlterada, String nomeAlterado, String observacao) {
        this.produto = produto;
        this.tipo = tipo;
        this.observacao = observacao;
        this.valorAlterado = valorAlterado;
        this.quantidadeAlterada = quantidadeAlterada;
        this.nomeAlterado = nomeAlterado;
    } 
    
    public Long getId() {
        return id;
    }
    public Long getProdutoId(){
        return produto.getId();
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValorAlterado() {
        return valorAlterado;
    }

    public Integer getQuantidadeAlterada() {
        return quantidadeAlterada;
    }

    public String getNomeAlterado() {
        return nomeAlterado;
    }
    
}


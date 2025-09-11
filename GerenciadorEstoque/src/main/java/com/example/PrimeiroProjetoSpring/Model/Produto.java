
package com.example.PrimeiroProjetoSpring.Model;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Table(name = "tb_produtos")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String nome;
    
    private BigDecimal preco;
    private int quantidade;
    
    private LocalDateTime dataAdicao;
    private LocalDateTime dataModificacao;
    
    //vários produtos para 1 categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    
    //métodos utilitários para formatar a data e hora de criação e modificação de objetos no banco
    @PrePersist
    public void aoCriar() {
        dataAdicao = LocalDateTime.now().withNano(0);
        dataModificacao = LocalDateTime.now().withNano(0);
    }

    @PreUpdate
    public void aoAtualizar() {
        dataModificacao = LocalDateTime.now().withNano(0);
    }
    
    public Produto(){}
    
    public Produto(String nome, BigDecimal preco, int quantidade, Categoria categoria){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

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
    
    public LocalDateTime getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDateTime dataAdicao) {
        this.dataAdicao = dataAdicao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    public Categoria getCategoria(){
        return categoria;
    } 
    
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }
    
}

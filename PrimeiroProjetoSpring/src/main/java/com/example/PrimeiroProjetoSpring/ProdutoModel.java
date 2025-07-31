
package com.example.PrimeiroProjetoSpring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_produtos")
public class ProdutoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDateTime dataAdicao;
    
    public ProdutoModel(){}
    
    public ProdutoModel(String nome, double preco, int quantidade, LocalDateTime dataAdicao){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataAdicao = dataAdicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
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
    
    
    
    
}

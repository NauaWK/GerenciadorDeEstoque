
package com.example.GerenciadorEstoque.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String nome;
    
    private int qtd_produtos = 0;
    
    //1 categoria para vários produtos
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;
    
    public Categoria(){}
    
    public Categoria(String nome){
        this.nome = nome;
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

    public int getQuantidade() {
        return qtd_produtos;
    }

    public void setQuantidade(int qtd_produtos) {
        this.qtd_produtos = qtd_produtos;
    }
   
}

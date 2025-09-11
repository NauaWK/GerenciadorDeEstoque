
package com.example.PrimeiroProjetoSpring.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String nome;
    
    private int quantidade_produtos = 0;
    
    //1 categoria para vários produtos
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();
    
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
    
    public int getQuantidade(){
        return quantidade_produtos;
    } 
    
    private void atualizarQuantidade() {
        this.quantidade_produtos = produtos.size();
    }   
    
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        atualizarQuantidade(); 
    }
    
    public void removerProduto(Produto produto){
        produtos.remove(produto);
        atualizarQuantidade();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    
}

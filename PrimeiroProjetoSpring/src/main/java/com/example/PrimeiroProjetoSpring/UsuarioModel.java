
package com.example.PrimeiroProjetoSpring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tb_usuarios")
public class UsuarioModel {
    
    @Id
    private String id; 
    
    private String nome;
    private int idade;
    
    public UsuarioModel(){}
    
    public UsuarioModel(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
}

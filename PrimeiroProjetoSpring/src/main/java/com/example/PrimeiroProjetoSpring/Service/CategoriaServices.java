
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.Controller.CategoriaController;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;

public class CategoriaServices {
    
    private final CategoriaController categoriaController;
    private final CategoriaRepository categoriaRepository;
    
    public CategoriaServices(CategoriaController categoriaController, CategoriaRepository categoriaRepository){
        this.categoriaController = categoriaController;
        this.categoriaRepository = categoriaRepository;
        
    }
    
    //adicionando uma categoria
    public void adicionarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    
}

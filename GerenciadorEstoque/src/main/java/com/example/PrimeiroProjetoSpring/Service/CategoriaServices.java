
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServices {
    
    private final CategoriaRepository categoriaRepository;
    
    public CategoriaServices(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;        
    }
    
    //adicionando uma categoria
    public void adicionarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
       
}

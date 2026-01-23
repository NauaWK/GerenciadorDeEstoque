
package com.example.GerenciadorEstoque.repositories;

import com.example.GerenciadorEstoque.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    public boolean existsByNome(String nome);

}


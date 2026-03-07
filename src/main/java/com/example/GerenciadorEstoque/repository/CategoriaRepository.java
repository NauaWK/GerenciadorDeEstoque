
package com.example.GerenciadorEstoque.repository;

import com.example.GerenciadorEstoque.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    public boolean existsByNome(String nome);

}


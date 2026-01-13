
package com.example.GerenciadorEstoque.Repository;

import com.example.GerenciadorEstoque.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    public boolean existsByNome(String nome);

}


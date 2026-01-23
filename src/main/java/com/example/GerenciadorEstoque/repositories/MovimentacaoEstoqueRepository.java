
package com.example.GerenciadorEstoque.repositories;

import com.example.GerenciadorEstoque.entities.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    
}


package com.example.GerenciadorEstoque.Repository;

import com.example.GerenciadorEstoque.Model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    
}

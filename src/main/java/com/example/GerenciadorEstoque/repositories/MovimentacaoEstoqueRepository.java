
package com.example.GerenciadorEstoque.repositories;

import com.example.GerenciadorEstoque.entities.MovimentacaoEstoque;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    
    @Query("SELECT me FROM MovimentacaoEstoque me WHERE me.produto.id = :productId")
    List<MovimentacaoEstoque> findStockMovementsByProduct(@Param("productId")Long id);
    
}

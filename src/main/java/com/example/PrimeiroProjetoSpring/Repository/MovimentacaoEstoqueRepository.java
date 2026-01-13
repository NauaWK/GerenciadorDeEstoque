
package com.example.PrimeiroProjetoSpring.Repository;

import com.example.PrimeiroProjetoSpring.Model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    
}


package com.example.PrimeiroProjetoSpring.Repository;

import com.example.PrimeiroProjetoSpring.Model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByCategoriaId(Long id);

    boolean existsByNome(String nome);
    
}




package com.example.GerenciadorEstoque.Repository;

import com.example.GerenciadorEstoque.Model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByCategoriaId(Long id);
    
    boolean existsByCategoriaId(Long categoriaId);
    
    boolean existsByNome(String nome);
    
}



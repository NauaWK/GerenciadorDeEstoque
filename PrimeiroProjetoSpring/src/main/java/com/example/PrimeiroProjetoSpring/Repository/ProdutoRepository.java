
package com.example.PrimeiroProjetoSpring.Repository;

import com.example.PrimeiroProjetoSpring.Model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {}



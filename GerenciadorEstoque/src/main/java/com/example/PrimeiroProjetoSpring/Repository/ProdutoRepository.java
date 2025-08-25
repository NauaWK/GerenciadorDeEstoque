
package com.example.PrimeiroProjetoSpring.Repository;

import com.example.PrimeiroProjetoSpring.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}



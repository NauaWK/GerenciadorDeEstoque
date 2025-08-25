
package com.example.PrimeiroProjetoSpring.Repository;

import com.example.PrimeiroProjetoSpring.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{}

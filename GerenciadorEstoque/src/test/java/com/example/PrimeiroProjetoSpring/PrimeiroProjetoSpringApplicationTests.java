package com.example.PrimeiroProjetoSpring;

import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class PrimeiroProjetoSpringApplicationTests { 
      
	@Test
	void deveAtualizarQuantidadeProdutosDeUmaCategoria() {
                       
            Categoria categoria = new Categoria("Alimentos");
            Produto produto = new Produto("Café", BigDecimal.valueOf(20.5), 20, categoria);                
	
            categoria.adicionarProduto(produto);
            Assertions.assertEquals(1, categoria.getQuantidade());   
		
            categoria.removerProduto(produto);
            Assertions.assertEquals(0, categoria.getQuantidade());
            
    }
}

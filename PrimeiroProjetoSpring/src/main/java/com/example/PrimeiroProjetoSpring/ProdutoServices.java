
package com.example.PrimeiroProjetoSpring;

import com.example.PrimeiroProjetoSpring.ProdutosDTO.ProdutoRequestDTO;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServices {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoServices(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    
    //conversão de ProdutoRequestDTO para ProdutoModel
    public ProdutoModel convertDtoToModel(ProdutoRequestDTO produto){
        ProdutoModel produtoSalvo = new ProdutoModel(
                produto.nome(), 
                produto.preco(), 
                produto.quantidade(),
                LocalDateTime.now()
        );        
        return produtoRepository.save(produtoSalvo);
    }
       
}

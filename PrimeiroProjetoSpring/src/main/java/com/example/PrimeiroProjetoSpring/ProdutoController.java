package com.example.PrimeiroProjetoSpring;

import com.example.PrimeiroProjetoSpring.ProdutosDTO.ProdutoRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProdutoController {
    
    private final ProdutoServices produtoServices;
    
    public ProdutoController(ProdutoServices produtoServices){
        this.produtoServices = produtoServices;
    }
    
    @PostMapping("/adicionarProduto")
    public ProdutoModel criarProduto(@RequestBody ProdutoRequestDTO produto){
        ProdutoModel produtoSalvo = produtoServices.convertDtoToModel(produto);             
        return produtoSalvo;      
    }
    
    
    @GetMapping("/ola")
    public String ola(){
        System.out.println("Chamando endpoint /ola para teste");
        return "olá";
    }
        
     
}
    

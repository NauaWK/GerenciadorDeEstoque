package com.example.PrimeiroProjetoSpring;

import com.example.PrimeiroProjetoSpring.ProdutosDTO.ProdutoRequestDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
public class ProdutoController {
    
    private final ProdutoServices produtoServices;
    
    public ProdutoController(ProdutoServices produtoServices){
        this.produtoServices = produtoServices;
    }
    
    @PostMapping("/adicionarProduto")
    public ProdutoModel adicionarProduto(@RequestBody ProdutoRequestDTO produto){
        ProdutoModel produtoSalvo = produtoServices.convertDtoToModel(produto);             
        return produtoSalvo;      
    }
       
    @GetMapping("/listarProdutos")
    public List<ProdutoModel> listarProdutos(){
        return produtoServices.listarProdutos(); 
    }
    
    @PutMapping("/atualizarProduto/{id}")
    public Optional<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO novoProdutoRequest){
        return produtoServices.atualizarProduto(id, novoProdutoRequest);      
    }  
}
    

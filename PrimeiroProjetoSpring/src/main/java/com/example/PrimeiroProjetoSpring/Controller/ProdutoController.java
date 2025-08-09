package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.Model.ProdutoModel;
import com.example.PrimeiroProjetoSpring.Service.ProdutoServices;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoRequestDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<?> adicionarProduto(@RequestBody ProdutoRequestDTO produto){
        ProdutoModel produtoSalvo = produtoServices.convertDtoToModel(produto); 
        produtoServices.adicionarProduto(produtoSalvo);
        return ResponseEntity.ok().body(produtoSalvo);      
    }
       
    @GetMapping("/listarProdutos")
    public List<ProdutoModel> listarProdutos(){
        return produtoServices.listarProdutos(); 
    }
    
    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO novoProdutoRequest){
        return produtoServices.atualizarProduto(id, novoProdutoRequest);      
    }  
    
    @DeleteMapping("/deletarProduto/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id){
        return produtoServices.deletarProduto(id);
    }
}
    

package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Service.ProdutoServices;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.ProdutoMapper;
import jakarta.validation.Valid;
import java.net.URI;
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
    private final ProdutoMapper produtoMapper;
    
    public ProdutoController(ProdutoServices produtoServices, ProdutoMapper produtoMapper){
        this.produtoServices = produtoServices;
        this.produtoMapper = produtoMapper;
    }
    
    @PostMapping("/adicionarProduto")
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(@Valid @RequestBody ProdutoRequestDTO produto){
        Produto produtoSalvo = produtoMapper.convertDtoToModel(produto); 
        produtoServices.adicionarProduto(produtoMapper.convertDtoToModel(produto));
        return ResponseEntity.created(URI.create("/estoque/adicionarProduto/" + produtoSalvo.getId())).body(produtoMapper.convertProdutoToDTO(produtoSalvo));      
    }
       
    @GetMapping("/listarProdutos")
    public List<Produto> listarProdutos(){
        return produtoServices.listarProdutos(); 
    }
    
    @GetMapping("/listarProdutos/{id}")
    public ResponseEntity<ProdutoResponseDTO> listarProdutoPorId(@PathVariable Long id){
        return produtoServices.listarProdutoPorId(id);
    }
        
    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO novoProdutoRequest){
        return produtoServices.atualizarProduto(id, novoProdutoRequest);      
    }  
    
    @DeleteMapping("/deletarProduto/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id){
        return produtoServices.deletarProduto(id);
    }
}
    

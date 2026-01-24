package com.example.GerenciadorEstoque.controller;

import com.example.GerenciadorEstoque.services.ProdutoServices;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoRequestDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.docs.ProdutoControllerDoc;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
@Tag(name = "Produtos", description = "Endpoint para gerenciamento de produtos")
public class ProdutoController implements ProdutoControllerDoc{
    
    private final ProdutoServices produtoServices;
    
    public ProdutoController(ProdutoServices produtoServices){
        this.produtoServices = produtoServices;
    }
    
    @PostMapping("/produtos")
    @Override
    public ResponseEntity<ProdutoResponseDTO> addProduct(@Valid @RequestBody ProdutoRequestDTO produto){
        ProdutoResponseDTO dto = produtoServices.addProduct(produto);
        return ResponseEntity.created(URI.create("/estoque/produtos/" + dto.id())).body(dto);      
    }
       
    @GetMapping("/produtos")
    @Override
    public ResponseEntity<List<ProdutoResponseDTO>> listAllProducts(){
        List<ProdutoResponseDTO> dtos = produtoServices.listAllProducts();
        return ResponseEntity.ok().body(dtos);
    }
    
    @GetMapping("/produtos/{id}")
    @Override
    public ResponseEntity<ProdutoResponseDTO> findProductById(@PathVariable Long id){
        ProdutoResponseDTO dto = produtoServices.findProductById(id);
        return ResponseEntity.ok().body(dto);
    }
    
    @DeleteMapping("/produtos/{id}")
    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        produtoServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
    

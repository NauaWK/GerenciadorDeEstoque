
package com.example.GerenciadorEstoque.controller;

import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.docs.CategoriaControllerDoc;
import com.example.GerenciadorEstoque.services.CategoriaServices;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Categorias", description = "Endpoint para gerenciamento de categorias")
public class CategoriaController implements CategoriaControllerDoc {
    
    private final CategoriaServices categoriaServices;
    
    public CategoriaController(CategoriaServices categoriaServices){
        this.categoriaServices = categoriaServices;    
    }  
    
    @PostMapping("/categorias")
    @Override
    public ResponseEntity<CategoriaResponseDTO> addCategory (@Valid @RequestBody CategoriaRequestDTO request){
        CategoriaResponseDTO dto = categoriaServices.addNewCategory(request);           
        return ResponseEntity.created(URI.create("/estoque/categorias/" + dto.id())).body(dto);          
    }    
    
    @GetMapping("/categorias")
    @Override
    public ResponseEntity<List<CategoriaResponseDTO>> listAllCategories(){
        List<CategoriaResponseDTO> categorias =  categoriaServices.listAllCategories();
        return ResponseEntity.ok().body(categorias);
    }
    
    @GetMapping("/categorias/{id}")
    @Override
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(@PathVariable Long id){
        CategoriaResponseDTO categoria = categoriaServices.findCategoryById(id);
        return ResponseEntity.ok().body(categoria);
    }   
    
    @GetMapping("/categorias/{id}/produtos")
    @Override
    public ResponseEntity<List<ProdutoResponseDTO>> listProductsByCategory(@PathVariable Long id){
        List<ProdutoResponseDTO> produtos = categoriaServices.listProductsByCategory(id);
        return ResponseEntity.ok().body(produtos);
    } 
    
    @PutMapping("/categorias/{id}")
    @Override
    public ResponseEntity<CategoriaResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoriaRequest){
        CategoriaResponseDTO categoria = categoriaServices.updateCategory(id, categoriaRequest);
        return ResponseEntity.ok().body(categoria);      
    }  
    
    @DeleteMapping("/categorias/{id}")
    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoriaServices.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
}
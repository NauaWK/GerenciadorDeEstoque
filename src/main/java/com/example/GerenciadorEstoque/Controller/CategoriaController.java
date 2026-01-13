
package com.example.GerenciadorEstoque.Controller;

import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.Docs.CategoriaControllerDoc;
import com.example.GerenciadorEstoque.Exception.customExceptions.ObjectAlreadyExistsException;
import com.example.GerenciadorEstoque.Utils.Mappers.CategoriaMapper;
import com.example.GerenciadorEstoque.Model.Categoria;
import com.example.GerenciadorEstoque.Service.CategoriaServices;
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
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias")
public class CategoriaController implements CategoriaControllerDoc {
    
    private final CategoriaMapper categoriaMapper;
    private final CategoriaServices categoriaServices;
    
    public CategoriaController(CategoriaServices categoriaServices, CategoriaMapper categoriaMapper){
        this.categoriaMapper = categoriaMapper;
        this.categoriaServices = categoriaServices;    
    }  
    
    @PostMapping("/categorias")
    @Override
    public ResponseEntity<CategoriaResponseDTO> addCategory (@Valid @RequestBody CategoriaRequestDTO categoriaRequest){

        if(categoriaServices.categoryAlreadyExists(categoriaRequest.nome())){
            throw new ObjectAlreadyExistsException("A categoria com nome "+categoriaRequest.nome()+" já existe.");
        }

        Categoria categoriaSalva = categoriaMapper.convertDtoToCategoria(categoriaRequest);
        categoriaServices.addCategory(categoriaSalva);           
        return ResponseEntity.created(URI.create("/estoque/categorias/" + categoriaSalva.getId())).body(categoriaMapper.convertCategoriaToDto(categoriaSalva));          
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

package com.example.GerenciadorEstoque.Controller;

import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.Exception.customExceptions.ObjectAlreadyExistsException;
import com.example.GerenciadorEstoque.Utils.Mappers.CategoriaMapper;
import com.example.GerenciadorEstoque.Model.Categoria;
import com.example.GerenciadorEstoque.Service.CategoriaServices;
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
public class CategoriaController {
    
    private final CategoriaMapper categoriaMapper;
    private final CategoriaServices categoriaServices;
    
    public CategoriaController(CategoriaServices categoriaServices, CategoriaMapper categoriaMapper){
        this.categoriaMapper = categoriaMapper;
        this.categoriaServices = categoriaServices;    
    }  
    
    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseDTO> addCategory (@Valid @RequestBody CategoriaRequestDTO categoriaRequest){

        if(categoriaServices.categoryAlreadyExists(categoriaRequest.nome())){
            throw new ObjectAlreadyExistsException("A categoria com nome "+categoriaRequest.nome()+" já existe.");
        }

        Categoria categoriaSalva = categoriaMapper.convertDtoToCategoria(categoriaRequest);
        categoriaServices.addCategory(categoriaSalva);           
        return ResponseEntity.created(URI.create("/estoque/categorias/" + categoriaSalva.getId())).body(categoriaMapper.convertCategoriaToDto(categoriaSalva));          
    }    
    
    @GetMapping("/categorias")
    public List<CategoriaResponseDTO> listAllCategories(){
        return categoriaServices.listAllCategories();
    }
    
    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(@PathVariable Long id){
        return categoriaServices.findCategoryById(id);
    }   
    
    @GetMapping("/categorias/{id}/produtos")
    public List<ProdutoResponseDTO> listProductsByCategory(@PathVariable Long id){
        return categoriaServices.listProductsByCategory(id);
    } 
    
    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoriaRequest){
        return categoriaServices.updateCategory(id, categoriaRequest);      
    }  
    
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        return categoriaServices.deleteCategory(id);
    }
    
}
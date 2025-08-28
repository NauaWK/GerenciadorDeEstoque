
package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.CategoriaMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Service.CategoriaServices;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CategoriaResponseDTO> adicionarCategoria (CategoriaRequestDTO categoriaRequest){
        Categoria categoriaSalva = categoriaMapper.convertDtoToCategoria(categoriaRequest);
        categoriaServices.adicionarCategoria(categoriaSalva);           
        return ResponseEntity.created(URI.create("/estoque/categorias/" + categoriaSalva.getId())).body(categoriaMapper.convertCategoriaToDto(categoriaSalva));          
    }    
    
    @GetMapping("/categorias")
    public List<CategoriaResponseDTO> listarCategorias(){
        return categoriaServices.listarCategorias();
    }
    
    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseDTO> listarProdutoPorId(@PathVariable Long id){
        return categoriaServices.listarCategoriaPorId(id);
    }   
}
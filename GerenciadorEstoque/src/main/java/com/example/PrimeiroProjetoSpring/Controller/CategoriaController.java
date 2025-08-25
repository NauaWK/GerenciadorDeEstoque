
package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.CategoriaMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Service.CategoriaServices;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class CategoriaController {
    
    private final CategoriaMapper categoriaMapper;
    private final CategoriaServices categoriaService;
    
    public CategoriaController(CategoriaServices categoriaService, CategoriaMapper categoriaMapper){
        this.categoriaMapper = categoriaMapper;
        this.categoriaService = categoriaService;    
    }  
    
    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseDTO> adicionarCategoria (CategoriaRequestDTO categoriaRequest){
        Categoria categoriaSalva = categoriaMapper.convertDtoToCategoria(categoriaRequest);
        categoriaService.adicionarCategoria(categoriaSalva);           
        return ResponseEntity.created(URI.create("/estoque/categorias/" + categoriaSalva.getId())).body(categoriaMapper.convertCategoriaToDTO(categoriaSalva));          
    }    
}

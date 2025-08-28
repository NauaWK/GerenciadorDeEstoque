
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.CategoriaMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriaServices {
    
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    
    public CategoriaServices(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper){
        this.categoriaRepository = categoriaRepository;        
        this.categoriaMapper = categoriaMapper;
    }
    
    //adicionando uma categoria
    public void adicionarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    //listar todas as categorias
    public List<CategoriaResponseDTO> listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        //convertendo cada Categoria em CategoriaResponseDTO
        List<CategoriaResponseDTO> responseDtos = categorias.stream().map(categoria -> new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao())).collect(Collectors.toList());
        
        return responseDtos;
    }      
    
    //buscando uma categoria pelo ID   
    public ResponseEntity<CategoriaResponseDTO> listarCategoriaPorId(Long id){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));      
        return ResponseEntity.ok().body(categoriaMapper.convertCategoriaToDto(categoriaExistente));      
    }
    
    
}

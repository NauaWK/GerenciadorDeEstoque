
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.CategoriaMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
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
    private final ProdutoRepository produtoRepository;
    
    public CategoriaServices(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper, ProdutoRepository produtoRepository){
        this.categoriaRepository = categoriaRepository;        
        this.categoriaMapper = categoriaMapper;
        this.produtoRepository = produtoRepository;
    }
    
    //adicionando uma categoria
    public void adicionarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    //buscando uma categoria pelo ID
    public Categoria buscarCategoria(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria com id "+id+" não encontrada."));
    }
    
    //listar todas as categorias
    public List<CategoriaResponseDTO> listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        //convertendo cada Categoria em CategoriaResponseDTO
        List<CategoriaResponseDTO> responseDtos = categorias.stream().map(categoria -> new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome())).collect(Collectors.toList());
        
        return responseDtos;
    }      
    
    //buscando uma categoria pelo ID   
    public ResponseEntity<CategoriaResponseDTO> listarCategoriaPorId(Long id){
        Categoria categoriaExistente = buscarCategoria(id);     
        return ResponseEntity.ok().body(categoriaMapper.convertCategoriaToDto(categoriaExistente));      
    }  
    
    //listando produtos de uma categoria
    public List<ProdutoResponseDTO> listarProdutosDaCategoria(Long id){
        buscarCategoria(id);        
        //acessando os produtos de uma categoria pelo ID através de um método personalizado em ProdutoRepository
        List<Produto> produtos = produtoRepository.findByCategoriaId(id);     
        
        //convertendo cada Produto em ProdutoResponseDTO
        List<ProdutoResponseDTO> responseDtos = produtos.stream().map(produto -> new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(), 
            produto.getPreco(), 
            produto.getQuantidade(),
            produto.getDataAdicao(),
            produto.getDataModificacao(),
            produto.getCategoria().getId())).collect(Collectors.toList());
        
        return responseDtos;
    }
    
    //atualizando uma categoria
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria (Long id, CategoriaRequestDTO categoriaRequeset){
        Categoria categoriaExistente = buscarCategoria(id);
        
        //se estiver presente altera os dados
        categoriaExistente.setNome(categoriaRequeset.nome());     
        adicionarCategoria(categoriaExistente);
        return ResponseEntity.ok().body(categoriaMapper.convertCategoriaToDto(categoriaExistente));       
    }
    
    //deletando uma categoria com a rota DELETE
    public ResponseEntity<Void> deletarCategoria(Long id){
        buscarCategoria(id);
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}


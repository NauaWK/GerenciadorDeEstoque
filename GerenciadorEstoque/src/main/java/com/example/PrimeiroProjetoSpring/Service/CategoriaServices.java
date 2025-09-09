
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.CategoryNotFoundException;
import com.example.PrimeiroProjetoSpring.Mapper.CategoriaMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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
    public void addCategory(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    //buscando uma categoria pelo ID
    public Categoria findCategory(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria com id "+id+" não encontrada."));
    }

    public boolean categoryAlreadyExists(String nome){
        return categoriaRepository.existsByNome(nome);

    }
    
    //listar todas as categorias
    public List<CategoriaResponseDTO> listAllCategories(){
        List<Categoria> categorias = categoriaRepository.findAll();
        //convertendo cada Categoria em CategoriaResponseDTO
        List<CategoriaResponseDTO> responseDtos = categorias.stream().map(categoria -> new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome())).collect(Collectors.toList());
        
        return responseDtos;
    }      
    
    //buscando uma categoria pelo ID   
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(Long id){
        Categoria categoriaExistente = findCategory(id);     
        return ResponseEntity.ok(categoriaMapper.convertCategoriaToDto(categoriaExistente));      
    }  
    
    //listando produtos de uma categoria
    public List<ProdutoResponseDTO> listProductsByCategory(Long id){
        findCategory(id);
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
    public ResponseEntity<CategoriaResponseDTO> updateCategory (Long id, CategoriaRequestDTO categoriaRequest){
        Categoria categoriaExistente = findCategory(id);
        
        //se estiver presente, altera os dados
        categoriaExistente.setNome(categoriaRequest.nome());     
        addCategory(categoriaExistente);
        return ResponseEntity.ok(categoriaMapper.convertCategoriaToDto(categoriaExistente));       
    }
    

    //deletando uma categoria
    public ResponseEntity<Void> deleteCategory(Long id){
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}


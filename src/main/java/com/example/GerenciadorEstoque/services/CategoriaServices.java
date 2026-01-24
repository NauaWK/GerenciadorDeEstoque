
package com.example.GerenciadorEstoque.services;

import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.exception.customExceptions.CategoryWithProductsException;
import com.example.GerenciadorEstoque.exception.customExceptions.ObjectAlreadyExistsException;
import com.example.GerenciadorEstoque.exception.customExceptions.ObjectNotFoundException;
import com.example.GerenciadorEstoque.utils.mappers.CategoriaMapper;
import com.example.GerenciadorEstoque.entities.Categoria;
import com.example.GerenciadorEstoque.entities.Produto;
import com.example.GerenciadorEstoque.repositories.CategoriaRepository;
import com.example.GerenciadorEstoque.repositories.ProdutoRepository;
import com.example.GerenciadorEstoque.utils.mappers.ProdutoMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServices {
    
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    
    public CategoriaServices(
            CategoriaRepository categoriaRepository, 
            CategoriaMapper categoriaMapper, 
            ProdutoRepository produtoRepository,
            ProdutoMapper produtoMapper){
        this.categoriaRepository = categoriaRepository;        
        this.categoriaMapper = categoriaMapper;
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }
       
    public Categoria findCategory(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria com id "+id+" não encontrada."));
    }
    
    private boolean categoryAlreadyExists(String nome){
        return categoriaRepository.existsByNome(nome);
    }
      
    
    public void saveCategory(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    public CategoriaResponseDTO addNewCategory(CategoriaRequestDTO request){
        if(categoryAlreadyExists(request.nome())){
            throw new ObjectAlreadyExistsException("A categoria com nome "+request.nome()+" já existe.");
        }
        Categoria categoria = categoriaMapper.toCategoria(request);
        categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);
    }
    
    public CategoriaResponseDTO findCategoryById(Long id){
        Categoria categoriaExistente = findCategory(id);    
        return categoriaMapper.toDto(categoriaExistente);      
    }  
    
    public List<CategoriaResponseDTO> listAllCategories(){
        List<Categoria> categorias = categoriaRepository.findAll();
        
        //convertendo cada Categoria em CategoriaResponseDTO
        List<CategoriaResponseDTO> responseDtos = categorias.stream()
                .map(categoriaMapper::toDto)
                .toList();
        
        return responseDtos;
    }      

    public List<ProdutoResponseDTO> listProductsByCategory(Long id){
        
        findCategory(id);
        
        //acessando os produtos de uma categoria pelo ID através de um método personalizado em ProdutoRepository
        List<Produto> produtos = produtoRepository.findByCategoriaId(id);    
        
        //convertendo cada Produto em ProdutoResponseDTO
        List<ProdutoResponseDTO> responseDtos = produtos.stream()
                .map(produtoMapper::toDto)
                .toList();
        
        return responseDtos;
    }
    
    public CategoriaResponseDTO updateCategory (Long id, CategoriaRequestDTO categoriaRequest){
        Categoria categoria = findCategory(id);
        
        //se estiver presente, altera os dados
        categoria.setNome(categoriaRequest.nome());     
        categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);       
    }
    
    public void deleteCategory(Long id){
        if(produtoRepository.existsByCategoriaId(id)){
            throw new CategoryWithProductsException("Não é possível deletar esta categoria no momento pois há pelo menos 1 produto associado a ela.");
        }
        findCategory(id);
        categoriaRepository.deleteById(id);
    }
    
}


package com.example.GerenciadorEstoque.Service;

import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.DTO.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.Exception.customExceptions.CategoryWithProductsException;
import com.example.GerenciadorEstoque.Exception.customExceptions.ObjectNotFoundException;
import com.example.GerenciadorEstoque.Utils.Mappers.CategoriaMapper;
import com.example.GerenciadorEstoque.Model.Categoria;
import com.example.GerenciadorEstoque.Model.Produto;
import com.example.GerenciadorEstoque.Repository.CategoriaRepository;
import com.example.GerenciadorEstoque.Repository.ProdutoRepository;
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
    
    public void addCategory(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    
    public Categoria findCategory(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria com id "+id+" não encontrada."));
    }
    
    public boolean categoryAlreadyExists(String nome){
        return categoriaRepository.existsByNome(nome);
    }
       
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(Long id){
        Categoria categoriaExistente = findCategory(id);    
        CategoriaResponseDTO dto = categoriaMapper.convertCategoriaToDto(categoriaExistente);
        return ResponseEntity.ok(dto);      
    }  
    
    public List<CategoriaResponseDTO> listAllCategories(){
        List<Categoria> categorias = categoriaRepository.findAll();
        
        //convertendo cada Categoria em CategoriaResponseDTO
        List<CategoriaResponseDTO> responseDtos = categorias.stream().map(categoria -> new CategoriaResponseDTO(
            categoria.getId(),
            categoria.getNome(),
            categoria.getQuantidade())).collect(Collectors.toList());
        
        return responseDtos;
    }      

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
    
    public ResponseEntity<CategoriaResponseDTO> updateCategory (Long id, CategoriaRequestDTO categoriaRequest){
        Categoria categoriaExistente = findCategory(id);
        
        //se estiver presente, altera os dados
        categoriaExistente.setNome(categoriaRequest.nome());     
        addCategory(categoriaExistente);
        return ResponseEntity.ok(categoriaMapper.convertCategoriaToDto(categoriaExistente));       
    }
    
    public ResponseEntity<Void> deleteCategory(Long id){
        if(produtoRepository.existsByCategoriaId(id)){
            throw new CategoryWithProductsException("Não é possível deletar esta categoria no momento pois há pelo menos 1 produto associado a ela.");
        }
        findCategory(id);
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}

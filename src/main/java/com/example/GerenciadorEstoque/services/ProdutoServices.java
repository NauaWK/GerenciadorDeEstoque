
package com.example.GerenciadorEstoque.services;

import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoRequestDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.exception.customExceptions.ObjectAlreadyExistsException;
import com.example.GerenciadorEstoque.exception.customExceptions.ObjectNotFoundException;
import com.example.GerenciadorEstoque.entities.Categoria;
import com.example.GerenciadorEstoque.utils.mappers.ProdutoMapper;
import com.example.GerenciadorEstoque.entities.Produto;
import com.example.GerenciadorEstoque.repositories.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;
    private final CategoriaServices categoriaServices;
    private final ProdutoMapper produtoMapper;

    public ProdutoServices(ProdutoRepository produtoRepository, CategoriaServices categoriaServices, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaServices = categoriaServices;
        this.produtoMapper = produtoMapper;
    }

    public Produto findProduct(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com id "+ id +" não encontrado."));
    }
    
    private boolean productAlreadyExists(String nome) {
        return produtoRepository.existsByNome(nome);
    }

    public ProdutoResponseDTO findProductById (Long id){
        Produto produtoExistente = findProduct(id);
        return produtoMapper.toDto(produtoExistente);
    }
    
    public void saveProduct(Produto product){
        produtoRepository.save(product);
    }
    
    public ProdutoResponseDTO addProduct(ProdutoRequestDTO request) {  
        
        //verificando se a categoria selecionada no requestDTO existe através do ID
        Categoria categoriaExistente = categoriaServices.findCategory(request.categoriaId());
        
        //tratamento de nome duplicado
        if(productAlreadyExists(request.nome())){
            throw new ObjectAlreadyExistsException("O produto com nome "+ request.nome()+" já existe.");
        }
        
        //adicionando o produto e atualizando a quantidade de produtos da categoria correspondente
        categoriaExistente.setQuantidade(categoriaExistente.getQuantidade() + request.quantidade());
        categoriaServices.saveCategory(categoriaExistente);  
        
        Produto produtoSalvo = produtoMapper.toProduto(request, categoriaExistente);    
        produtoRepository.save(produtoSalvo);
        return produtoMapper.toDto(produtoSalvo);
        
    }
    
    public List<ProdutoResponseDTO> listAllProducts () {
        List<Produto> produtos = produtoRepository.findAll();
        
        //convertendo cada Produto em ProdutoResponseDTO
        List<ProdutoResponseDTO> responseDtos = produtos.stream()
                .map(produtoMapper::toDto)
                .toList();

        return responseDtos;
    }

    public void deleteProduct (Long id){
        Produto produto = findProduct(id);    
        produtoRepository.deleteById(id);
       
        //atualizando quantidade de produtos da categoria correspondente
        Long categoriaId = produto.getCategoria().getId();
        Categoria categoria = categoriaServices.findCategory(categoriaId);
        categoria.setQuantidade(categoria.getQuantidade() - produto.getQuantidade());
        categoriaServices.saveCategory(categoria);
    }
}

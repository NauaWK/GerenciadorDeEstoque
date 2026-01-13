
package com.example.GerenciadorEstoque.Service;

import com.example.GerenciadorEstoque.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.Exception.customExceptions.ObjectNotFoundException;
import com.example.GerenciadorEstoque.Model.Categoria;
import com.example.GerenciadorEstoque.Utils.Mappers.ProdutoMapper;
import com.example.GerenciadorEstoque.Model.Produto;
import com.example.GerenciadorEstoque.Repository.ProdutoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
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
                .orElseThrow(() -> new ObjectNotFoundException("Produto com id "+id+" não encontrado."));
    }
    
     public boolean productAlreadyExists(String nome) {
        return produtoRepository.existsByNome(nome);
    }

    public ProdutoResponseDTO findProductById (Long id){
        Produto produtoExistente = findProduct(id);
        return produtoMapper.convertProdutoToDTO(produtoExistente);
    }
    
    public void addProduct(Produto produto) {          
        produtoRepository.save(produto);
        
        //adicionando o produto e atualizando a quantidade de produtos da categoria correspondente
        Long categoriaId = produto.getCategoria().getId();
        Categoria categoria = categoriaServices.findCategory(categoriaId);
        categoria.setQuantidade(categoria.getQuantidade() + produto.getQuantidade());
        categoriaServices.addCategory(categoria);  
    }
    
    public List<ProdutoResponseDTO> listAllProducts () {
        List<Produto> produtos = produtoRepository.findAll();
        
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

    public void deleteProduct (Long id){
        Produto produto = findProduct(id);    
        produtoRepository.deleteById(id);
       
        //atualizando quantidade de produtos da categoria correspondente
        Long categoriaId = produto.getCategoria().getId();
        Categoria categoria = categoriaServices.findCategory(categoriaId);
        categoria.setQuantidade(categoria.getQuantidade() - produto.getQuantidade());
        categoriaServices.addCategory(categoria);
    }
}

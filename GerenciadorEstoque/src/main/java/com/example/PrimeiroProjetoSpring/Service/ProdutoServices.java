
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.ObjectNotFoundException;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Utils.Mappers.ProdutoMapper;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
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

    public void addProduct(Produto produto) {          
        produtoRepository.save(produto);
        
        //adicionando o produto e atualizando a quantidade de produtos da categoria correspondente
        Long categoriaId = produto.getCategoria().getId();
        Categoria categoria = categoriaServices.findCategory(categoriaId);
        categoria.setQuantidade(categoria.getQuantidade() + produto.getQuantidade());
        categoriaServices.addCategory(categoria);
  
    }

    public Produto findProduct(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com id "+id+" não encontrado."));
    }
    
     public boolean productAlreadyExists(String nome) {
        return produtoRepository.existsByNome(nome);
    }

    public ResponseEntity<ProdutoResponseDTO> findProductById (Long id){
        Produto produtoExistente = findProduct(id);
        ProdutoResponseDTO dto =  produtoMapper.convertProdutoToDTO(produtoExistente);
        return ResponseEntity.ok(dto);
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

    public ResponseEntity<Void> deleteProduct (Long id){
        Produto produto = findProduct(id);    
        produtoRepository.deleteById(id);
       
        //atualizando quantidade de produtos da categoria correspondente
        Long categoriaId = produto.getCategoria().getId();
        Categoria categoria = categoriaServices.findCategory(categoriaId);
        categoria.setQuantidade(categoria.getQuantidade() - produto.getQuantidade());
        categoriaServices.addCategory(categoria);
        
        return ResponseEntity.noContent().build();
    }
}


package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.ProdutoMapper;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoServices {
    
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    
    public ProdutoServices(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper){
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }
     
    //método utilitário para adicionar um produto ao repository
    public void adicionarProduto(Produto produto){
        produtoRepository.save(produto);
    }
    
    //acessando todos os produtos do Repository
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    
    //buscando um produto no banco pelo ID   
    public ResponseEntity<ProdutoResponseDTO> listarProdutoPorId(Long id){
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));      
        return ResponseEntity.ok().body(produtoMapper.convertProdutoToDTO(produtoExistente));      
    }
       
    //atualizando um produto com a rota PUT
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(Long id, ProdutoRequestDTO novoProdutoRequest){
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        //se estiver presente no banco altera os dados
        produtoExistente.setNome(novoProdutoRequest.nome());
        produtoExistente.setPreco(novoProdutoRequest.preco());
        produtoExistente.setQuantidade(novoProdutoRequest.quantidade()); 
        
        adicionarProduto(produtoExistente);
        return ResponseEntity.ok().body(produtoMapper.convertProdutoToDTO(produtoExistente));
    }
    
    //deletando um produto com a rota DELETE
    public ResponseEntity<?> deletarProduto(Long id){
        produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

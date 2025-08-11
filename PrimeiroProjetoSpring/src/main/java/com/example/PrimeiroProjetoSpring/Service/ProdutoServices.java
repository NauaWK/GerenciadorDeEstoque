
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoRequestDTO;
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
    
    //acessando todos os produtos do Repository para a rota GET /listarProdutos
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    
    //buscando um produto no banco pelo ID pela rota GET /listarProdutos/id
    
    public ResponseEntity<?> listarProdutoPorId(Long id){
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));      
        return ResponseEntity.ok().body(produtoExistente);      
    }
       
    //atualizando um produto com a rota PUT /atualizarProduto/id
    public ResponseEntity<?> atualizarProduto(Long id, ProdutoRequestDTO novoProdutoRequest){
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        //se estiver presente no banco altera os dados;
        produtoExistente.setNome(novoProdutoRequest.getNome());
        produtoExistente.setPreco(novoProdutoRequest.getPreco());
        produtoExistente.setQuantidade(novoProdutoRequest.getQuantidade()); 
        
        adicionarProduto(produtoExistente);
        return ResponseEntity.ok().body(produtoMapper.convertProdutoToDTO(produtoExistente));
    }
    
    //deletando um produto com a rota DELETE /deletarProduto/id
    public ResponseEntity<?> deletarProduto(Long id){
        Produto produtoSelecionado = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().body("Produto deletado:\n" + produtoMapper.convertProdutoToDTO(produtoSelecionado));
    }
}


package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.Model.ProdutoModel;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoServices {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoServices(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    
    //conversão de ProdutoRequestDTO para ProdutoModel
    public ProdutoModel convertDtoToModel(ProdutoRequestDTO produto){
        ProdutoModel produtoSalvo = new ProdutoModel(
                produto.getNome(), 
                produto.getPreco(), 
                produto.getQuantidade()               
        );      
        return produtoSalvo;
    }
    
    //método utilitário para adicionar um produto ao repository
    public void adicionarProduto(ProdutoModel produto){
        produtoRepository.save(produto);
    }
    
    //acessando todos os produtos do Repository para a rota GET /listarProdutos
    public List<ProdutoModel> listarProdutos(){
        List<ProdutoModel> listaProdutos = produtoRepository.findAll();
        return listaProdutos;
    }
    
    //buscando um produto no banco pelo ID pela rota GET /listarProdutos/id
    
    public ResponseEntity<?> listarProdutoPorId(Long id){
        ProdutoModel produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));      
        return ResponseEntity.ok().body(produtoExistente);      
    }
     
    
    //atualizando um produto com a rota PUT /atualizarProduto/id
    public ResponseEntity<?> atualizarProduto(Long id, ProdutoRequestDTO novoProdutoRequest){
        ProdutoModel produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        //se estiver presente no banco altera os dados      
        ProdutoModel produtoAtualizado = convertDtoToModel(novoProdutoRequest);
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade()); 
        
        adicionarProduto(produtoExistente);
        return ResponseEntity.ok().body(produtoExistente);
    }
    
    //deletando um produto com a rota DELETE /deletarProduto/id
    public ResponseEntity<?> deletarProduto(Long id){
        ProdutoModel produtoSelecionado = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().body("Produto deletado:\n" + "ID - " + produtoSelecionado.getId() + " Nome - " + produtoSelecionado.getNome());
    }
}

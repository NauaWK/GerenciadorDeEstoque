
package com.example.PrimeiroProjetoSpring;

import com.example.PrimeiroProjetoSpring.ProdutosDTO.ProdutoRequestDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServices {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoServices(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    
    //conversão de ProdutoRequestDTO para ProdutoModel
    public ProdutoModel convertDtoToModel(ProdutoRequestDTO produto){
        ProdutoModel produtoSalvo = new ProdutoModel(
                produto.nome(), 
                produto.preco(), 
                produto.quantidade(),
                LocalDateTime.now()
        );        
        return produtoRepository.save(produtoSalvo);
    }
    
    //acessando todos os produtos do Repository para a rota GET /listarProdutos
    public List<ProdutoModel> listarProdutos(){
        List<ProdutoModel> listaProdutos = produtoRepository.findAll();
        return listaProdutos;
    }
    
    //atualizando um produto com a rota PUT /atualizarProduto/{id}
    public Optional<ProdutoModel> atualizarProduto(Long id, ProdutoRequestDTO novoProdutoRequest){
        ProdutoModel novoProduto = convertDtoToModel(novoProdutoRequest);
        Optional<ProdutoModel> produtoEncontrado = produtoRepository.findById(id);
        
        //se estiver presente no banco altera os dados
        produtoEncontrado.ifPresent(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            produto.setQuantidade(novoProduto.getQuantidade());
            produto.setDataAdicao(novoProduto.getDataAdicao());
        });
        return produtoEncontrado;
    }
    
}

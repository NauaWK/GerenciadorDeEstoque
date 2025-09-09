
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Exception.customExceptions.ObjectNotFoundException;
import com.example.PrimeiroProjetoSpring.Mapper.ProdutoMapper;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Repository.ProdutoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoServices(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    //método utilitário para adicionar um produto ao repository
    public void addProduct(Produto produto) {
        produtoRepository.save(produto);
    }

    //buscando um produto pelo ID
    private Produto findProduct(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com id "+id+" não encontrado."));
    }

    public boolean productAlreadyExists(String nome) {
        return produtoRepository.existsByNome(nome);
    }

    //acessando todos os produtos do Repository
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

    //buscando um produto no banco pelo ID
    public ResponseEntity<ProdutoResponseDTO> findProductById (Long id){
        Produto produtoExistente = findProduct(id);
        return ResponseEntity.ok(produtoMapper.convertProdutoToDTO(produtoExistente));
    }

    //atualizando um produto com a rota PUT
    public ResponseEntity<ProdutoResponseDTO> updateProduct (Long id, ProdutoRequestDTO novoProdutoRequest){
        Produto produtoExistente = findProduct(id);

        //se estiver presente no banco altera os dados
        produtoExistente.setNome(novoProdutoRequest.nome());
        produtoExistente.setPreco(novoProdutoRequest.preco());
        produtoExistente.setQuantidade(novoProdutoRequest.quantidade());
        addProduct(produtoExistente);
        return ResponseEntity.ok(produtoMapper.convertProdutoToDTO(produtoExistente));
    }

    //deletando um produto com a rota DELETE
    public ResponseEntity<Void> deleteProduct (Long id){
        findProduct(id);
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

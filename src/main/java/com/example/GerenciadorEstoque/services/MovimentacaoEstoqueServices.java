
package com.example.GerenciadorEstoque.services;

import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.GerenciadorEstoque.entities.Categoria;
import com.example.GerenciadorEstoque.entities.MovimentacaoEstoque;
import com.example.GerenciadorEstoque.entities.Produto;
import com.example.GerenciadorEstoque.exception.customExceptions.ObjectNotFoundException;
import com.example.GerenciadorEstoque.repositories.MovimentacaoEstoqueRepository;
import com.example.GerenciadorEstoque.utils.mappers.MovimentacaoEstoqueMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoEstoqueServices {
    
    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final MovimentacaoEstoqueMapper movimentacaoMapper;
    private final ProdutoServices produtoServices;
    private final CategoriaServices categoriaServices;

    public MovimentacaoEstoqueServices(
            MovimentacaoEstoqueRepository movimentacaoRepository, 
            MovimentacaoEstoqueMapper movimentacaoMapper, 
            ProdutoServices produtoServices,
            CategoriaServices categoriaServices) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoMapper = movimentacaoMapper;
        this.produtoServices = produtoServices;
        this.categoriaServices = categoriaServices;
    }
       
    public MovimentacaoResponseDTO registerStockMovement(MovimentacaoRequestDTO request){
        Produto produto =  produtoServices.findProduct(request.produtoId());  
        MovimentacaoEstoque movimentacao = movimentacaoMapper.toMovimentacao(produto, request); 
        
        switch(request.tipo()){           
            case NOME -> {
                produto.setNome(request.nomeAlterado());
                movimentacao.setQuantidadeAlterada(0);
                movimentacao.setValorAlterado(null);
            }
            case VALOR -> {
                produto.setPreco(request.valorAlterado());           
                movimentacao.setNomeAlterado(null);
                movimentacao.setQuantidadeAlterada(0);
            }
            case QUANTIDADE -> {         
                
                //atualizando a quantidade de produtos da categoria
                Categoria categoria = categoriaServices.findCategory(produto.getCategoria().getId());
                int qtdAnterior = produto.getQuantidade();
                produto.setQuantidade(request.quantidadeAlterada());
                int qtdAtual = produto.getQuantidade();
                categoria.setQuantidade(categoria.getQuantidade() + (qtdAtual - qtdAnterior));
                categoriaServices.saveCategory(categoria);
                movimentacao.setNomeAlterado(null);
                movimentacao.setValorAlterado(null);
            }            
        }
           
        produtoServices.saveProduct(produto);        
        movimentacaoRepository.save(movimentacao);
        return movimentacaoMapper.toDto(movimentacao);
    }  
    
    public MovimentacaoEstoque findMovimentacao(Long id){
        return movimentacaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Movimentação com id "+ id +" não encontrada."));
    }
    
    public List<MovimentacaoResponseDTO> getAllStockMovement(){
        List<MovimentacaoEstoque> movimentacoes = movimentacaoRepository.findAll();
       
        List<MovimentacaoResponseDTO> responseDtos = movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .toList();
        return responseDtos;
    }
    
    public MovimentacaoResponseDTO findStockMovementById(Long id){
        MovimentacaoEstoque movimentacao = findMovimentacao(id);
        return movimentacaoMapper.toDto(movimentacao);
    }
    
    public List<MovimentacaoResponseDTO> findStockMovementsByProduct(Long id){
        
        //verificando se o produto existe
        produtoServices.findProduct(id);
        
        List<MovimentacaoEstoque> movimentacoes = movimentacaoRepository.findStockMovementsByProduct(id);
        
        List<MovimentacaoResponseDTO> responseDtos = movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .toList();
        return responseDtos;        
    }   
    
}

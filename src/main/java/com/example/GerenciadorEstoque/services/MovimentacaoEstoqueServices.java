
package com.example.GerenciadorEstoque.services;

import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.GerenciadorEstoque.entities.Categoria;
import com.example.GerenciadorEstoque.entities.MovimentacaoEstoque;
import com.example.GerenciadorEstoque.entities.Produto;
import com.example.GerenciadorEstoque.repositories.MovimentacaoEstoqueRepository;
import com.example.GerenciadorEstoque.utils.mappers.MovimentacaoEstoqueMapper;
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
        switch(request.tipo()){           
            case NOME -> produto.setNome(request.nomeAlterado());
            case VALOR -> produto.setPreco(request.valorAlterado());
            case QUANTIDADE -> {         
                
                //atualizando a quantidade de produtos da categoria
                Categoria categoria = categoriaServices.findCategory(produto.getCategoria().getId());
                int qtdAnterior = produto.getQuantidade();
                produto.setQuantidade(request.quantidadeAlterada());
                int qtdAtual = produto.getQuantidade();
                categoria.setQuantidade(categoria.getQuantidade() + (qtdAtual - qtdAnterior));
                categoriaServices.saveCategory(categoria);
            }            
        }
           
        produtoServices.saveProduct(produto);        
        MovimentacaoEstoque movimentacao = movimentacaoMapper.toMovimentacao(produto, request); 
        movimentacaoRepository.save(movimentacao);
        return movimentacaoMapper.toDto(movimentacao);
    }  
    
}

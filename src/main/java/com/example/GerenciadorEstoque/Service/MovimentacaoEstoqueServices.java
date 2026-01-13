
package com.example.GerenciadorEstoque.Service;

import com.example.GerenciadorEstoque.Model.MovimentacaoEstoque;
import com.example.GerenciadorEstoque.Repository.MovimentacaoEstoqueRepository;
import com.example.GerenciadorEstoque.Utils.Mappers.MovimentacaoEstoqueMapper;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoEstoqueServices {
    
    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final MovimentacaoEstoqueMapper movimentacaoMapper;

    public MovimentacaoEstoqueServices(MovimentacaoEstoqueRepository movimentacaoRepository, MovimentacaoEstoqueMapper movimentacaoMapper) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoMapper = movimentacaoMapper;
    }
       
    public void registerStockMovement(MovimentacaoEstoque movimentacaoRegistrada){
        movimentacaoRepository.save(movimentacaoRegistrada);
    }  
    
}

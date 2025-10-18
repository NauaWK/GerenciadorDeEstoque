
package com.example.PrimeiroProjetoSpring.Service;

import com.example.PrimeiroProjetoSpring.Model.MovimentacaoEstoque;
import com.example.PrimeiroProjetoSpring.Repository.MovimentacaoEstoqueRepository;
import com.example.PrimeiroProjetoSpring.Utils.Mappers.MovimentacaoEstoqueMapper;
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


package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.DTO.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.PrimeiroProjetoSpring.Model.MovimentacaoEstoque;
import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Service.MovimentacaoEstoqueServices;
import com.example.PrimeiroProjetoSpring.Service.ProdutoServices;
import com.example.PrimeiroProjetoSpring.Utils.Mappers.MovimentacaoEstoqueMapper;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
public class MovimentacaoEstoqueController {
    
    private final MovimentacaoEstoqueServices movimentacaoServices;
    private final MovimentacaoEstoqueMapper movimentacaoMapper;
    private final ProdutoServices produtoServices;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueServices movimentacaoServices, MovimentacaoEstoqueMapper movimentacaoMapper, ProdutoServices produtoServices) {
        this.movimentacaoServices = movimentacaoServices;
        this.movimentacaoMapper = movimentacaoMapper;
        this.produtoServices = produtoServices;
    }
    
    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoResponseDTO> registerStockMovement (@Valid @RequestBody MovimentacaoRequestDTO movimentacaoRequest){
        
       Produto produtoExistente =  produtoServices.findProduct(movimentacaoRequest.produtoId());     
       MovimentacaoEstoque movimentacaoRegistrada = movimentacaoMapper.convertDtoToMovimentacao(produtoExistente, movimentacaoRequest);   
       movimentacaoServices.registerStockMovement(movimentacaoRegistrada);
       
       MovimentacaoResponseDTO dto = movimentacaoMapper.convertMovimentacaoToDto(movimentacaoRegistrada);
       return ResponseEntity.created(URI.create("/estoque/movimentacoes/" + movimentacaoRegistrada.getId())).body(dto);
               
    }
     
}

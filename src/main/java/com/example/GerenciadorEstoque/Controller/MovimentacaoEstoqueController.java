
package com.example.GerenciadorEstoque.controller;

import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.GerenciadorEstoque.services.MovimentacaoEstoqueServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
@Tag(name = "MovimentacaoEstoque", description = "Endpoint para modificações de qualquer produto do estoque")
public class MovimentacaoEstoqueController {
    
    private final MovimentacaoEstoqueServices movimentacaoServices;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueServices movimentacaoServices) {
        this.movimentacaoServices = movimentacaoServices;
    }
    
    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoResponseDTO> registerStockMovement (@Valid @RequestBody MovimentacaoRequestDTO movimentacaoRequest){             
       MovimentacaoResponseDTO dto = movimentacaoServices.registerStockMovement(movimentacaoRequest);
       return ResponseEntity.created(URI.create("/estoque/movimentacoes/" + dto.id())).body(dto);
              
    }
     
}

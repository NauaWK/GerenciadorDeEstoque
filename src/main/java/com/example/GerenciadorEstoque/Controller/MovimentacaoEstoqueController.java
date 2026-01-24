
package com.example.GerenciadorEstoque.controller;

import com.example.GerenciadorEstoque.docs.MovimentacaoEstoqueControllerDoc;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import com.example.GerenciadorEstoque.services.MovimentacaoEstoqueServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
@Tag(name = "MovimentacaoEstoque", description = "Endpoint para modificações de qualquer produto do estoque")
public class MovimentacaoEstoqueController implements MovimentacaoEstoqueControllerDoc {
    
    private final MovimentacaoEstoqueServices movimentacaoServices;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueServices movimentacaoServices) {
        this.movimentacaoServices = movimentacaoServices;
    }
    
    @PostMapping("/movimentacoes")
    @Override
    public ResponseEntity<MovimentacaoResponseDTO> registerStockMovement (@Valid @RequestBody MovimentacaoRequestDTO movimentacaoRequest){             
       MovimentacaoResponseDTO dto = movimentacaoServices.registerStockMovement(movimentacaoRequest);
       return ResponseEntity.created(URI.create("/estoque/movimentacoes/" + dto.id())).body(dto);
              
    }
    
    @GetMapping("/movimentacoes")
    @Override
    public ResponseEntity<List<MovimentacaoResponseDTO>> getAllStockMovements(){
        List<MovimentacaoResponseDTO> dtos = movimentacaoServices.getAllStockMovement();
        return ResponseEntity.ok().body(dtos);
    }
    
    @GetMapping("/movimentacoes/{id}")
    @Override
    public ResponseEntity<MovimentacaoResponseDTO> findStockMovementById(@PathVariable Long id){
        MovimentacaoResponseDTO dto = movimentacaoServices.findStockMovementById(id);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("/movimentacoes/produto/{id}")
    @Override
    public ResponseEntity<List<MovimentacaoResponseDTO>> findStockMovementsByProduct(@PathVariable Long id){
        List<MovimentacaoResponseDTO> dtos = movimentacaoServices.findStockMovementsByProduct(id);
        return ResponseEntity.ok().body(dtos);
    }
    
}


package com.example.GerenciadorEstoque.docs;

import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoRequestDTO;
import com.example.GerenciadorEstoque.dto.MovimentacaoEstoqueDTOs.MovimentacaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MovimentacaoEstoqueControllerDoc {
      
   @Operation(summary = "Registrar movimentação", description = "Registrar uma nova movimentação de estoque")
   @ApiResponses(value = {       
        @ApiResponse(responseCode = "201", description = "Cadastro realizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "ID do produto não encontrado"),
    })
   ResponseEntity<MovimentacaoResponseDTO> registerStockMovement (MovimentacaoRequestDTO movimentacaoRequest);
   
    @Operation(summary = "Listar movimentações", description = "Busca todas as movimentações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada")
    })
    ResponseEntity<List<MovimentacaoResponseDTO>> getAllStockMovements();
      
    @Operation(summary = "Buscar por ID", description = "Busca uma movimentação pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    ResponseEntity<MovimentacaoResponseDTO> findStockMovementById(Long id);   
    
    @Operation(summary = "Buscar movimentações de um produto pelo ID", description = "Busca todas as movimentações de um produto através do ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada"),
        @ApiResponse(responseCode = "404", description = "ID do produto não encontrado")
    })
    ResponseEntity<List<MovimentacaoResponseDTO>> findStockMovementsByProduct(Long id);
}


package com.example.GerenciadorEstoque.docs;

import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoRequestDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ProdutoControllerDoc {
    
    @Operation(summary = "Cadastrar produto", description = "Adiciona um novo produto")
    @ApiResponses(value = {       
        @ApiResponse(responseCode = "201", description = "Cadastro realizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Produto já existente")
    })
    public ResponseEntity<ProdutoResponseDTO> addProduct(ProdutoRequestDTO produtoRequest);
    
    @Operation(summary = "Listar produtos", description = "Busca todos os produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada")
    })
    public ResponseEntity<List<ProdutoResponseDTO>> listAllProducts();
    
    @Operation(summary = "Buscar por ID", description = "Busca um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    public ResponseEntity<ProdutoResponseDTO> findProductById(Long id);
    
    @Operation(summary = "Deletar produto", description = "Deleta um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto deletado"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    public ResponseEntity<Void> deleteProduct(Long id);
}

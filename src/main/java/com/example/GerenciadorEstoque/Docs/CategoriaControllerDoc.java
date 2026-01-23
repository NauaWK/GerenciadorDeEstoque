
package com.example.GerenciadorEstoque.docs;

import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaRequestDTO;
import com.example.GerenciadorEstoque.dto.CategoriaDTOs.CategoriaResponseDTO;
import com.example.GerenciadorEstoque.dto.ProdutoDTOs.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CategoriaControllerDoc {
    
    @Operation(summary = "Cadastrar categoria", description = "Adiciona uma nova categoria")
    @ApiResponses(value = {       
        @ApiResponse(responseCode = "201", description = "Cadastro realizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Categoria já existente")
    })
    public ResponseEntity<CategoriaResponseDTO> addCategory (CategoriaRequestDTO categoriaRequest);
    
    @Operation(summary = "Listar categorias", description = "Busca todas as categorias")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada")
    })
    public ResponseEntity<List<CategoriaResponseDTO>> listAllCategories();
    
    @Operation(summary = "Buscar por ID", description = "Busca uma categoria pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(Long id);
    
    @Operation(summary = "Buscar produtos da categoria", description = "Busca produtos de uma categoria pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    public ResponseEntity<List<ProdutoResponseDTO>> listProductsByCategory(Long id);
    
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    public ResponseEntity<CategoriaResponseDTO> updateCategory(Long id, CategoriaRequestDTO categoriaRequest);
    
    @Operation(summary = "Deletar categoria", description = "Deleta uma categoria pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria deletada"),
        @ApiResponse(responseCode = "404", description = "ID não encontrado")
    })
    public ResponseEntity<Void> deleteCategory(Long id);
    
}

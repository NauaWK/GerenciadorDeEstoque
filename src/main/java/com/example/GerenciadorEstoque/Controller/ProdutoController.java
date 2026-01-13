package com.example.GerenciadorEstoque.Controller;

import com.example.GerenciadorEstoque.Exception.customExceptions.ObjectAlreadyExistsException;
import com.example.GerenciadorEstoque.Model.Produto;
import com.example.GerenciadorEstoque.Service.ProdutoServices;
import com.example.GerenciadorEstoque.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.GerenciadorEstoque.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.GerenciadorEstoque.Docs.ProdutoControllerDoc;
import com.example.GerenciadorEstoque.Utils.Mappers.ProdutoMapper;
import com.example.GerenciadorEstoque.Model.Categoria;
import com.example.GerenciadorEstoque.Service.CategoriaServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController implements ProdutoControllerDoc{
    
    private final ProdutoServices produtoServices;
    private final CategoriaServices categoriaServices;
    private final ProdutoMapper produtoMapper;
    
    public ProdutoController(
            ProdutoServices produtoServices, 
            ProdutoMapper produtoMapper,
            CategoriaServices categoriaServices
            ){
        
        this.produtoServices = produtoServices;
        this.produtoMapper = produtoMapper;
        this.categoriaServices = categoriaServices;
    }
    
    @PostMapping("/produtos")
    @Override
    public ResponseEntity<ProdutoResponseDTO> addProduct(@Valid @RequestBody ProdutoRequestDTO produtoRequest){

        //verificando se a categoria selecionada no requestDTO existe através do ID
        Categoria categoriaExistente = categoriaServices.findCategory(produtoRequest.categoriaId());

        if(produtoServices.productAlreadyExists(produtoRequest.nome())){
            throw new ObjectAlreadyExistsException("O produto com nome "+ produtoRequest.nome()+" já existe.");
        }

        Produto produtoSalvo = produtoMapper.convertDtoToProduto(produtoRequest, categoriaExistente);
        produtoServices.addProduct(produtoSalvo);
        ProdutoResponseDTO dto = produtoMapper.convertProdutoToDTO(produtoSalvo);
        return ResponseEntity.created(URI.create("/estoque/produtos/" + produtoSalvo.getId())).body(dto);      
    }
       
    @GetMapping("/produtos")
    @Override
    public ResponseEntity<List<ProdutoResponseDTO>> listAllProducts(){
        List<ProdutoResponseDTO> produtos = produtoServices.listAllProducts();
        return ResponseEntity.ok().body(produtos);
    }
    
    @GetMapping("/produtos/{id}")
    @Override
    public ResponseEntity<ProdutoResponseDTO> findProductById(@PathVariable Long id){
        ProdutoResponseDTO produto = produtoServices.findProductById(id);
        return ResponseEntity.ok().body(produto);
    }
    
    @DeleteMapping("/produtos/{id}")
    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        produtoServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
    

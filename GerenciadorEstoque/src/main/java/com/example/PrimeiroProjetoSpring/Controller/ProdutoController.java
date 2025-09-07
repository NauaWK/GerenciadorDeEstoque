package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Service.ProdutoServices;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.ProdutoMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;
import com.example.PrimeiroProjetoSpring.Service.CategoriaServices;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/estoque")
public class ProdutoController {
    
    private final ProdutoServices produtoServices;
    private final CategoriaServices categoriaServices;
    private final ProdutoMapper produtoMapper;
    
    public ProdutoController(ProdutoServices produtoServices, 
            ProdutoMapper produtoMapper, 
            CategoriaRepository categoriaRepository,
            CategoriaServices categoriaServices){
        
        this.produtoServices = produtoServices;
        this.produtoMapper = produtoMapper;
        this.categoriaServices = categoriaServices;
    }
    
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponseDTO> addProduct(@Valid @RequestBody ProdutoRequestDTO produto){
        //verificando se a categoria selecionada no requestDto existe através do ID
        Categoria categoriaExistente = categoriaServices.findCategory(produto.categoriaId());
        Produto produtoSalvo = produtoMapper.convertDtoToProduto(produto, categoriaExistente); 
        produtoServices.addProduct(produtoSalvo);
        return ResponseEntity.created(URI.create("/estoque/produtos/" + produtoSalvo.getId())).body(produtoMapper.convertProdutoToDTO(produtoSalvo));      
    }
       
    @GetMapping("/produtos")
    public List<ProdutoResponseDTO> listAllProducts(){
        return produtoServices.listAllProducts(); 
    }
    
    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> findProductById(@PathVariable Long id){
        return produtoServices.findProductById(id);
    }
        
    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO novoProdutoRequest){
        return produtoServices.updateProduct(id, novoProdutoRequest);      
    }  
    
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        return produtoServices.deleteProduct(id);
    }
}
    

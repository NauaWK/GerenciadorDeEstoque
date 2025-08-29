package com.example.PrimeiroProjetoSpring.Controller;

import com.example.PrimeiroProjetoSpring.Model.Produto;
import com.example.PrimeiroProjetoSpring.Service.ProdutoServices;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoRequestDTO;
import com.example.PrimeiroProjetoSpring.DTO.ProdutoDTOs.ProdutoResponseDTO;
import com.example.PrimeiroProjetoSpring.Mapper.ProdutoMapper;
import com.example.PrimeiroProjetoSpring.Model.Categoria;
import com.example.PrimeiroProjetoSpring.Repository.CategoriaRepository;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/estoque")
public class ProdutoController {
    
    private final ProdutoServices produtoServices;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;
    
    public ProdutoController(ProdutoServices produtoServices, ProdutoMapper produtoMapper, CategoriaRepository categoriaRepository){
        this.produtoServices = produtoServices;
        this.produtoMapper = produtoMapper;
        this.categoriaRepository = categoriaRepository;
    }
    
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(@Valid @RequestBody ProdutoRequestDTO produto){
        Categoria categoriaExistente = categoriaRepository.findById(produto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Produto produtoSalvo = produtoMapper.convertDtoToProduto(produto, categoriaExistente); 
        produtoServices.adicionarProduto(produtoSalvo);
        return ResponseEntity.created(URI.create("/estoque/produtos/" + produtoSalvo.getId())).body(produtoMapper.convertProdutoToDTO(produtoSalvo));      
    }
       
    @GetMapping("/produtos")
    public List<ProdutoResponseDTO> listarProdutos(){
        return produtoServices.listarProdutos(); 
    }
    
    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> listarProdutoPorId(@PathVariable Long id){
        return produtoServices.listarProdutoPorId(id);
    }
        
    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO novoProdutoRequest){
        return produtoServices.atualizarProduto(id, novoProdutoRequest);      
    }  
    
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        return produtoServices.deletarProduto(id);
    }
}
    

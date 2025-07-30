package com.example.PrimeiroProjetoSpring;

import com.example.PrimeiroProjetoSpring.UsuarioDtos.UsuarioRequestDTO;
import com.example.PrimeiroProjetoSpring.UsuarioDtos.UsuarioResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    
    private final UsuarioRepository usuarioRepository;
    
    public Controller(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    
    @PostMapping("/criarUsuario")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO usuario){
        
               
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);      
    }
    
    
    @GetMapping("/ola")
    public String ola(){
        System.out.println("Chamando endpoint /ola");
        return "olá";
    }
        
     
}
    

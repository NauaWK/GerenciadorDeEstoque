
package com.example.PrimeiroProjetoSpring.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTeste {   
    
    @GetMapping("/")
    public String bemvindo() {
        return "bemvindo!";
    }
    
    @GetMapping("/ola")
    public String hello() {
        return "Olá, Mundo(de Spring)!";
    }
       
}

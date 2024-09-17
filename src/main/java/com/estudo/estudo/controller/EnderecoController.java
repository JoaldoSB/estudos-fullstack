package com.estudo.estudo.controller;

import com.estudo.estudo.model.Endereco;
import com.estudo.estudo.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Endpoint para salvar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoSalvo = enderecoService.salvarEndereco(endereco);
        return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os endereços
    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        List<Endereco> enderecos = enderecoService.listarEnderecos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    // Endpoint para buscar um endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoService.buscarEnderecoPorId(id);
        return endereco.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para atualizar um endereço
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        try {
            Endereco endereco = enderecoService.atualizarEndereco(id, enderecoAtualizado);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para deletar um endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEnderecoPorId(@PathVariable Long id) {
        try {
            enderecoService.deletarEnderecoPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

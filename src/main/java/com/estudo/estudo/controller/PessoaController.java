package com.estudo.estudo.controller;

import com.estudo.estudo.model.Pessoa;
import com.estudo.estudo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")  // Define o endpoint base para as operações de Pessoa
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Endpoint para salvar uma nova pessoa
    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.salvarPessoa(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);  // Retorna 201 Created
    }

    // Endpoint para buscar todas as pessoas
    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTodas() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);  // Retorna 200 OK
    }

    // Endpoint para buscar uma pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(id);
        if (pessoa.isPresent()) {
            return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 Not Found se não achar a pessoa
        }
    }

    // Endpoint para atualizar uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        try {
            Pessoa pessoa = pessoaService.atualizarPessoa(id, pessoaAtualizada);
            return new ResponseEntity<>(pessoa, HttpStatus.OK);  // Retorna 200 OK após a atualização
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 Not Found se a pessoa não for encontrada
        }
    }

    // Endpoint para deletar uma pessoa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        pessoaService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retorna 204 No Content após a exclusão
    }
}

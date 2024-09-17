package com.estudo.estudo.service;

import com.estudo.estudo.model.Pessoa;
import com.estudo.estudo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Salvar uma nova pessoa
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Buscar todas as pessoas
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    // Atualizar uma pessoa
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        // Aqui assumimos que a pessoa existe, mas é sempre bom tratar o Optional
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    // Atualize os campos necessários
                    pessoa.setNome(pessoaAtualizada.getNome());
                    pessoa.setCpf(pessoaAtualizada.getCpf());
                    pessoa.setEmail(pessoaAtualizada.getEmail());
                    pessoa.setTelefone(pessoaAtualizada.getTelefone());
                    // Adicione outros campos conforme necessário
                    return pessoaRepository.save(pessoa);
                })
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
    }

    // Deletar uma pessoa por ID
    public void deletarPorId(Long id) {
        pessoaRepository.deleteById(id);
    }


}

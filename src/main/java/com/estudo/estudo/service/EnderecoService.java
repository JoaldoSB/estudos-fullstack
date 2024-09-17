package com.estudo.estudo.service;

import com.estudo.estudo.model.Endereco;
import com.estudo.estudo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Salvar um novo endereço
    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Buscar todos os endereços
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    // Buscar endereço por ID
    public Optional<Endereco> buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    // Atualizar um endereço
    public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setRua(enderecoAtualizado.getRua());
                    endereco.setCep(enderecoAtualizado.getCep());
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));
    }

    // Deletar um endereço por ID
    public void deletarEnderecoPorId(Long id) {
        enderecoRepository.deleteById(id);
    }
}

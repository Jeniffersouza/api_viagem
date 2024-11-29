package com.api.demo;
import  com.api.demo.Destino;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DestinoService {
    private final DestinoRepository destinoRepository;

    public DestinoService(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    // Método para cadastrar um destino
    public Destino cadastrarDestino(Destino destino) {
        return destinoRepository.save(destino); // Salva o destino no banco de dados
    }

    // Método para listar todos os destinos
    public List<Destino> listarDestinos() {
        return destinoRepository.findAll();
    }

    // Método para pesquisar destinos por nome ou localização
    public List<Destino> pesquisarDestinos(String query) {
        return destinoRepository.findByNomeContainingOrLocalizacaoContaining(query, query); // Pesquisa no banco
    }

    // Método para visualizar detalhes de um destino por ID
    public Optional<Destino> visualizarDetalhes(Long id) {
        return destinoRepository.findById(id); // Busca no banco por ID
    }

    // Método para excluir um destino
    public boolean excluirDestino(Long id) {
        if (destinoRepository.existsById(id)) {
            destinoRepository.deleteById(id); // Exclui o destino no banco
            return true;
        }
        return false;
    }

    // Método para avaliar um destino
    public Optional<Destino> avaliarDestino(Long id, double nota) {
        Optional<Destino> destinoOpt = destinoRepository.findById(id);
        destinoOpt.ifPresent(destino -> {
            destino.setAvaliacaoMedia(
                ((destino.getAvaliacaoMedia() * destino.getNumeroAvaliacoes()) + nota) 
                / (destino.getNumeroAvaliacoes() + 1)
            );
            destino.setNumeroAvaliacoes(destino.getNumeroAvaliacoes() + 1);
            destinoRepository.save(destino); // Atualiza a avaliação e salva no banco
        });
        return destinoOpt;
    }
}

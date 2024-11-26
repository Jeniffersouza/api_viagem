package com.api.demo;
import  com.api.demo.Destino;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DestinoService {
    private final List<Destino> destinos = new ArrayList<>();
    private long nextId = 1;

    public Destino cadastrarDestino(Destino destino) {
        destino.setId(nextId++);
        destinos.add(destino);
        return destino;
    }

    public List<Destino> listarDestinos() {
        return destinos;
    }

    public List<Destino> pesquisarDestinos(String query) {
        return destinos.stream()
                .filter(d -> d.getNome().toLowerCase().contains(query.toLowerCase()) ||
                             d.getLocalizacao().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    public Optional<Destino> visualizarDetalhes(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public boolean excluirDestino(Long id) {
        return destinos.removeIf(d -> d.getId().equals(id));
    }

    public Optional<Destino> avaliarDestino(Long id, double nota) {
        Optional<Destino> destinoOpt = visualizarDetalhes(id);
        destinoOpt.ifPresent(destino -> {
            destino.setAvaliacaoMedia(
                ((destino.getAvaliacaoMedia() * destino.getNumeroAvaliacoes()) + nota) 
                / (destino.getNumeroAvaliacoes() + 1)
            );
            destino.setNumeroAvaliacoes(destino.getNumeroAvaliacoes() + 1);
        });
        return destinoOpt;
    }
}

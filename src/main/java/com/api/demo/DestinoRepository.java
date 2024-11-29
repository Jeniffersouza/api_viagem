package com.api.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

    // Método para buscar destinos por nome ou localização
    List<Destino> findByNomeContainingOrLocalizacaoContaining(String nome, String localizacao);
}

package com.api.demo;
import  com.api.demo.Destino;
import com.api.demo.DestinoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {
    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        return ResponseEntity.ok(destinoService.cadastrarDestino(destino));
    }
    @GetMapping
    public ResponseEntity<?> listarDestinos() {
    List<Destino> destinos = destinoService.listarDestinos();
    if (destinos == null || destinos.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum destino encontrado.");
    }
    return ResponseEntity.ok(destinos);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam String query) {
        return ResponseEntity.ok(destinoService.pesquisarDestinos(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> visualizarDetalhes(@PathVariable Long id) {
        return destinoService.visualizarDetalhes(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        if (destinoService.excluirDestino(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    
}
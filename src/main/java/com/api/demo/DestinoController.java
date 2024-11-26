package com.api.demo;
import  com.api.demo.Destino;
import com.api.demo.DestinoService;
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
    public ResponseEntity<List<Destino>> listarDestinos() {
        return ResponseEntity.ok(destinoService.listarDestinos());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        if (destinoService.excluirDestino(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/avaliacao")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam double nota) {
        return destinoService.avaliarDestino(id, nota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
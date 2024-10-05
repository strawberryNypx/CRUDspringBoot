package br.com.fujideia.iesp.tecback.controller;
import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.model.dto.GeneroDTO;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController//criacao de api restufull indicando que a classe vai trabalhar com http
@RequestMapping("/genero")
@RequiredArgsConstructor
@Slf4j//pelo oq eu entendi e para achar os erros
public class GeneroController {

    private final GeneroService generoService;//referencia a regra de negocio

    @PostMapping
    public ResponseEntity<List<GeneroDTO>> listar(){
        log.info("chamar todos os generos");
        List<GeneroDTO> genero = generoService.listar();
        return ResponseEntity.ok(genero);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarPorId(@PathVariable Long id) {
        log.info("Chamando buscarPorId no GeneroController com id: {}", id);
        Optional<GeneroDTO> genero = generoService.buscarPorId(id);
        return genero.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> criarGenero(@RequestBody GeneroDTO generoDTO) {
        log.info("Chamando criarGenero no GeneroController com dados: {}", generoDTO);
        GeneroDTO generoCriado = generoService.criarGenero(generoDTO);
        return ResponseEntity.ok(generoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> atualizarGenero(@PathVariable Long id, @RequestBody GeneroDTO generoDTO) {
        log.info("Chamando atualizarGenero no GeneroController com id: {} e dados: {}", id, generoDTO);
        Optional<GeneroDTO> generoAtualizado = generoService.atualizarGenero(id, generoDTO);
        return generoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Long id) {
        log.info("Chamando deletarGenero no generoController com id: {}", id);
        boolean deletado = generoService.deletarGenero(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

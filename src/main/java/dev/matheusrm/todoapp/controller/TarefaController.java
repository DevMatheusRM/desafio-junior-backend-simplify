package dev.matheusrm.todoapp.controller;

import dev.matheusrm.todoapp.model.Tarefa;
import dev.matheusrm.todoapp.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        return tarefaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarefa createTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetails) {
        return tarefaService.findById(id)
                .map(tarefa -> {
                    tarefa.setNome(tarefaDetails.getNome());
                    tarefa.setDescricao(tarefaDetails.getDescricao());
                    tarefa.setRealizado(tarefaDetails.isRealizado());
                    tarefa.setPrioridade(tarefaDetails.getPrioridade());
                    Tarefa updatedTarefa = tarefaService.save(tarefa);
                    return ResponseEntity.ok(updatedTarefa);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        return tarefaService.findById(id)
                .map(tarefa -> {
                    tarefaService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

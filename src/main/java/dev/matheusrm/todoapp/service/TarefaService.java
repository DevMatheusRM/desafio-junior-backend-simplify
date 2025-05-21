package dev.matheusrm.todoapp.service;

import dev.matheusrm.todoapp.model.Tarefa;
import dev.matheusrm.todoapp.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa save(Tarefa tarefa) {
        if (tarefa.getDataCriacao() == null) {
            tarefa.setDataCriacao(LocalDateTime.now());
        }
        return tarefaRepository.save(tarefa);
    }

    public void deleteById(Long id) {
        tarefaRepository.deleteById(id);
    }
}

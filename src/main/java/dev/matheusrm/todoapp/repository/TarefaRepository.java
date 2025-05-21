package dev.matheusrm.todoapp.repository;

import dev.matheusrm.todoapp.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

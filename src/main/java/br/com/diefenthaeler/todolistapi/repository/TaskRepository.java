package br.com.diefenthaeler.todolistapi.repository;

import br.com.diefenthaeler.todolistapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

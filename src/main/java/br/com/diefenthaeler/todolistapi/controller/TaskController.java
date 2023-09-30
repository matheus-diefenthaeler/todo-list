package br.com.diefenthaeler.todolistapi.controller;

import br.com.diefenthaeler.todolistapi.dto.TaskRequest;
import br.com.diefenthaeler.todolistapi.entity.Task;
import br.com.diefenthaeler.todolistapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository repository;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskRequest request) {
        Task task = new Task(request.getDescription());

        Task save = repository.save(task);

        return ResponseEntity.ok().body(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Optional<Task> byId = repository.findById(id);
        var entity = byId.orElseThrow(() -> new RuntimeException("Task not found!"));
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> all = repository.findAll();

        return ResponseEntity.ok().body(all);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateById(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        Optional<Task> byId = repository.findById(id);
        var entity = byId.orElseThrow(() -> new RuntimeException("Task not found!"));

        entity.setCompleted(taskRequest.getCompleted());
        repository.save(entity);

        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Task> byId = repository.findById(id);
        byId.ifPresentOrElse(repository::delete, () -> {
            throw new RuntimeException("Address not found!");
        });
        return ResponseEntity.noContent().build();
    }
}

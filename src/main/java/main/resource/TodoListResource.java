package main.resource;


import jakarta.ws.rs.*;
import main.dto.TaskDto;
import main.model.Task;
import main.model.TaskRepository;
import main.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
public class TodoListResource {
  private final TodoListService todoListService;
  private final TaskRepository taskRepository;

  public TodoListResource(TodoListService todoListService, TaskRepository taskRepository) {
    this.todoListService = todoListService;
    this.taskRepository = taskRepository;
  }

  @PostMapping("/tasks/")
  public TaskDto addTask(Task task) {
    return todoListService.add(task.getName());
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<?> deleteID(@PathVariable long id) {
    Optional<Task> optionalTask = taskRepository.findById(id);

    if (!optionalTask.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    todoListService.deleteTask(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  private static void checkId(Long taskId) {
    if (taskId == null || taskId < 0) {
      throw new BadRequestException("TaskId bad.");
    }
  }


  @GetMapping("/tasks/{id}")
  public ResponseEntity<?> get(@PathVariable long id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (!optionalTask.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
  }

  @PutMapping("/tasks/{id}")
  public TaskDto PutID(Task task) {
    return todoListService.update(task.getId(),task.getName());
  }

  @DeleteMapping("/tasks/")
  public ResponseEntity<?> deleteID() {
    todoListService.deletaALL();
    return null;
  }

}

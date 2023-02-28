package main.dao;

import jakarta.inject.Inject;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskDao {

  private final TaskRepository taskRepository;

  @Inject
  public TaskDao(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void save(Task task) {
    if (task == null) {
      return;
    }
    taskRepository.save(task);
  }

  public Task get(Long id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    return optionalTask.get();
  }

  public ResponseEntity<?> delete(final Long id) {
    Optional<Task> optionalTask = taskRepository.findById(id);

    if (!optionalTask.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    taskRepository.deleteById(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }


  public List<Task> getAll() {
    Iterable<Task> taskIterable = taskRepository.findAll();

    List<Task> tasks = new ArrayList<>();
    for (Task task : taskIterable) {
      tasks.add(task);
    }
    return tasks;
  }

  public void deleteALL() {
    Iterable<Task> taskIterable = taskRepository.findAll();

    List<Task> tasks = new ArrayList<>();
    for (Task task : taskIterable) {
      taskRepository.delete(task);
    }
  }
}

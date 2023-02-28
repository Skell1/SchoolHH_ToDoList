package main.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import main.dao.TaskDao;
import main.dto.TaskDto;
import main.mapper.TaskMapper;
import main.model.Task;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TodoListService {
  private final TaskDao taskDao;


  @Inject
  public TodoListService(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  public TaskDto add(String taskName) {
    if (taskName.length()==0) {
      throw new BadRequestException("Task is empty.");
    }

    Task task = new Task(
        taskName,
            LocalDateTime.now());
    taskDao.save(task);
    return TaskMapper.taskToDto(task);
  }

  public TaskDto getTask(Long taskId) {
    Task task = taskDao.get(taskId);
    if (task==null) {
      throw new BadRequestException("Task not find.");
    }
    return TaskMapper.taskToDto(task);
  }

  public void deleteTask(Long taskId) {
    taskDao.delete(taskId);
  }

  public void deletaALL() {
    taskDao.deleteALL();
  }

  public TaskDto update(Long taskId, String taskName) {
    Task task = taskDao.get(taskId);
    task.setName(taskName);
    taskDao.save(task);
    return TaskMapper.taskToDto(task);
  }

}

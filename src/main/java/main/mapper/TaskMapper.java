package main.mapper;

import main.dto.TaskDto;
import main.model.Task;

import java.util.List;

public class TaskMapper {
  public static TaskDto taskToDto(Task task) {
    return new TaskDto(
            task.getId(),
        task.getName(),
        task.getTaskTime());
  }

  public static List<TaskDto> taskListToDtoList(List<Task> tasks) {
    return tasks.stream().map(TaskMapper::taskToDto).toList();
  }
}

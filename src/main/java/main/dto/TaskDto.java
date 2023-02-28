package main.dto;


import java.time.LocalDateTime;

public class TaskDto {

  private Long id;
  private String name;
  private LocalDateTime taskTime;

  public TaskDto(Long id, String name, LocalDateTime taskTime) {
    this.id = id;
    this.name = name;
    this.taskTime = taskTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getTaskTime() {
    return taskTime;
  }

  public void setTaskTime(LocalDateTime taskTime) {
    this.taskTime = taskTime;
  }
}

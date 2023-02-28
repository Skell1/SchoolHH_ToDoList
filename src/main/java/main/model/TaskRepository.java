package main.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TaskRepository extends JpaRepository<Task, Long> {
}

package main.resource;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class DefaultController {

    private final TaskRepository taskRepository;

    public DefaultController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();

        for (Task task : taskIterable) {
            tasks.add(task);
        }

        model.addAttribute("tasks", tasks)
                .addAttribute("tasksCount", tasks.size());

        return "index";
    }
}
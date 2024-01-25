package com.project.TaskManager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskServices taskServices;

    @GetMapping("/")
    // @ResponseBody
    public String getAllTasks(Model model) {
        List<Task> taskList = taskServices.getAllTasks();
        model.addAttribute("tasks", taskList);
        return "list";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Integer id, Model model) {
        Optional<Task> taskOptional = taskServices.getTaskById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            model.addAttribute("tasks", task);
            return "details";
        } else {
            return "redirect:/tasks";
        }
    }

    @GetMapping("/save")
    public String saveTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        Task savedTask = taskServices.saveTask(task);
        redirectAttributes.addFlashAttribute("success", "Task has been saved successfully");
        return "redirect:/tasks" + savedTask.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            taskServices.deleteTaskById(id);
            redirectAttributes.addFlashAttribute("DeleteTask", "Task Deleted Succesfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Error", "Unable to Delete " + e.getMessage());
        }
        return "redirect:/tasks";
    }
}
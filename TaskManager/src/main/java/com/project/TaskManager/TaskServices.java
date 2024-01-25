package com.project.TaskManager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServices {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Integer id) {
        return Optional.of(taskRepository.getReferenceById(id));
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

    // public void deleteAllTasks(){
    // taskRepository.deleteAll();
    // }
    //
    // public Long numberOfTasks(){
    // return taskRepository.count();
    // }
}

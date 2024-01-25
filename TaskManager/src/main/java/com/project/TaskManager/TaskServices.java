package com.project.TaskManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id){
        return Optional.of(taskRepository.getReferenceById(id));
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }

//    public void deleteAllTasks(){
//        taskRepository.deleteAll();
//    }
//
//    public Long numberOfTasks(){
//        return taskRepository.count();
//    }
}

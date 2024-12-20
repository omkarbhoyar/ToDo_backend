package com.InfoOrigin.ToDoApp.Service;

import com.InfoOrigin.ToDoApp.DTO.Tasks; // Correct import for Tasks
import com.InfoOrigin.ToDoApp.Repository.TaskRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepos taskRepo;

    public TaskService() {
        System.out.println("TaskService constructor");
    }

    public List<Tasks> getAllService() { // Use Tasks instead of Task
        System.out.println("getallservice Service method");
        return taskRepo.findAll();
    }


    public List<Tasks> getTasksByStatusService(boolean status) {
        System.out.println("getTasksByStatusService Service method");
        return taskRepo.findByCompleted(status);
    }
    public Tasks getTaskByIdService(int id) {
        Tasks task = taskRepo.findById(id).orElse(null);
        if (task == null) {
            throw new RuntimeException("Task not found for id: " + id);
        }
        return task;
    }
    public Tasks createTaskService(Tasks tasks) {
        System.out.println(" savetask Service method");
        System.out.println("Save service"+tasks);
        return taskRepo.save(tasks);
    }


    public void deleteTaskService(int id) {
        System.out.println("deletTaskService Service method");
        taskRepo.deleteById(id);

    }
    public void deleteMultiTaskService(String status) {
        System.out.println("deleteCompletedTaskService Service method");
        try { if (status.equals("Completed")) {
            List<Tasks> completedTasks = taskRepo.findByCompleted(true);
             taskRepo.deleteAll(completedTasks);
             } else if (status.equals("All")) {
                taskRepo.deleteAll();
            }

        }catch (Exception e) {
           System.out.println(e);
        }


    }

    public Tasks statusChangeService(int id) {
        Optional<Tasks> newStatus = taskRepo.findById(id);
        if (newStatus.isPresent()) {
            Tasks task = newStatus.get();
            task.setCompleted(!task.isCompleted());
            return taskRepo.save(task);
        } else {
            return null;
        }
    }

    public Tasks updateTaskService(int id, Tasks tasks) {
        System.out.println("updatetask Service method");

        Optional<Tasks> optionalTask = taskRepo.findById(id);
        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();

            task.setTitle(tasks.getTitle());

            return taskRepo.save(task);
        } else {
            // Handle the case where the task is not found
            throw new RuntimeException("Task not found with id " + id);
        }

    }

}

package com.InfoOrigin.ToDoApp.Controller;


import com.InfoOrigin.ToDoApp.DTO.Tasks; // Correct import for Tasks
import com.InfoOrigin.ToDoApp.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TaskController {

    @Autowired
    TaskService taskservice;


    public TaskController(){
        System.out.println("TaskController Constructor");
    }

    @GetMapping("/getall")
    public List<Tasks> getallTasks(){
        System.out.println("getallTasks Controller Method");
        return taskservice.getAllService();
    }
    @GetMapping("/getallbystatus/{status}")
    public List<Tasks> getTasksByStatus(@PathVariable ("status") boolean status){
        System.out.println("getTasksByStatus Controller Method");
        return taskservice.getTasksByStatusService(status);
    }


    @GetMapping("/getbyid/{id}")
    public Tasks getById(@PathVariable("id") int id) {
        System.out.println("getById Controller Method");
        return taskservice.getTaskByIdService(id);
    }

    @PostMapping("/create")
    public Tasks createTask(@RequestBody Tasks tasks){
        System.out.println("saveTasks Controller Method");
        System.out.println("Save controller "+tasks);
        return taskservice.createTaskService(tasks);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable int id){
        System.out.println("deletTask Controller Method");
         taskservice.deleteTaskService(id);

    }

    @DeleteMapping("/deleteall/{status}")
    public String deleteMultiTask(@PathVariable String status){
        System.out.println("deletCompletedTask Controller Method");
        taskservice.deleteMultiTaskService(status);
        return status+"Tasks deleted succesfylly";
    }

    @PutMapping("/StatusChange/{id}")
    public Tasks statusChange(@PathVariable int id) {
        return taskservice.statusChangeService(id);
    }

    @PutMapping("/update/{id}")
    public Tasks updateTask(@PathVariable int id, @RequestBody Tasks tasks){
        System.out.println("updateTask Controller Method");
        return taskservice.updateTaskService(id,tasks);

    }

}

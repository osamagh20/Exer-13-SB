package com.example.exer13sb.Controller;


import com.example.exer13sb.ApiResponse.ApiResponse;
import com.example.exer13sb.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/taskTracker")
public class TaskTrackerController {

    ArrayList<TaskTracker> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<TaskTracker>  getTasks(){
       return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTasks(@RequestBody TaskTracker task){
        tasks.add(task);
       return new ApiResponse("task added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTasks(@PathVariable int index,@RequestBody TaskTracker task){
        tasks.set(index, task);
        return new ApiResponse("task updated");

    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTasks(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("tasks deleted");

    }

    @PutMapping("/change/{index}")
    public ApiResponse changeTaskStatus(@PathVariable int index){
        for (int i = 0; i < tasks.size(); i++) {
            if(!tasks.get(i).getStatus().equalsIgnoreCase("done")){
                tasks.get(i).setStatus("Done");

            }

        }
        return new ApiResponse("task updated");
    }

    @GetMapping("/search/{title}")
    public TaskTracker searchTasks(@PathVariable String title){
        TaskTracker task = null;
        for (TaskTracker taskTracker : tasks) {
            if (taskTracker.getTitle().contains(title.toLowerCase())) {
                task = taskTracker;
            }
        }

        return task;
    }


}

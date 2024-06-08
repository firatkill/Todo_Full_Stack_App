package com.todoApp.todoApp.Controller;

import com.todoApp.todoApp.Model.DTO.RequestObject.TodoCategoryRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoCategoryResponse;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoResponse;
import com.todoApp.todoApp.Repository.TodoCategoryRepository;
import com.todoApp.todoApp.Service.Abstract.TodoCategoryService;
import com.todoApp.todoApp.Service.Abstract.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user/todocategories")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TodoCategoryController {
    private final TodoCategoryService todoCategoryService;



    @GetMapping("")
    public List<TodoCategoryResponse> getCategories(){
        return todoCategoryService.getAllCategories();
    }
    @PostMapping("")
    public void addCategory(@RequestBody TodoCategoryRequest categoryRequest){
        todoCategoryService.addCategory(categoryRequest);
    }
}

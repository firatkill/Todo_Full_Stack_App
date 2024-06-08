package com.todoApp.todoApp.Service.Abstract;

import com.todoApp.todoApp.Model.DTO.RequestObject.TodoCategoryRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoCategoryResponse;
import com.todoApp.todoApp.Model.Entity.TodoCategory;

import java.util.List;

public interface TodoCategoryService {
    void addCategory(TodoCategoryRequest categoryRequest);
    List<TodoCategoryResponse> getAllCategories();
}

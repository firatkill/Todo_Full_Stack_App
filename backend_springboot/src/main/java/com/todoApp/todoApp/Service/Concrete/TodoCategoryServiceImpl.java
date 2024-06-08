package com.todoApp.todoApp.Service.Concrete;

import com.todoApp.todoApp.Model.DTO.RequestObject.TodoCategoryRequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoCategoryResponse;
import com.todoApp.todoApp.Model.DTO.RequestObject.TodoResponse;
import com.todoApp.todoApp.Model.Entity.TodoCategory;
import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Repository.TodoCategoryRepository;
import com.todoApp.todoApp.Repository.TodoRepository;
import com.todoApp.todoApp.Repository.UserRepository;
import com.todoApp.todoApp.Service.Abstract.TodoCategoryService;
import com.todoApp.todoApp.Service.Abstract.TodoService;
import com.todoApp.todoApp.Service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoCategoryServiceImpl implements TodoCategoryService {
    private final TodoCategoryRepository repository;
    private final UserRepository userRepository;
    @Override
    public void addCategory(TodoCategoryRequest todoCategoryRequest) {
        TodoCategory todoCategory=new TodoCategory();

        todoCategory.setCategoryName(todoCategoryRequest.categoryName());
        todoCategory.setUser(getUser());
        repository.save(todoCategory);


    }

    @Override
    public List<TodoCategoryResponse> getAllCategories() {
        List<TodoCategory> categoryList=repository.findAll();
        List<TodoCategoryResponse> responseList=new ArrayList<>();
        categoryList.forEach(category->{
            TodoCategoryResponse categoryResponse= new TodoCategoryResponse();
            categoryResponse.convertFromCategory(category);
            responseList.add(categoryResponse);
        });
        return responseList;
    }

    private User getUser(){
        Optional<User> user= userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if(user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("User not found, todo cant be added.");
        }
    }
}

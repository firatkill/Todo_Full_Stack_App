package com.todoApp.todoApp.Repository;

import com.todoApp.todoApp.Model.Entity.TodoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoCategoryRepository extends JpaRepository< TodoCategory,Integer> {
    TodoCategory findTodoCategoryByCategoryName(String categoryName);
}

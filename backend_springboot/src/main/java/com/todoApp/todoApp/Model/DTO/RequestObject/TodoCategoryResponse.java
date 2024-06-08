package com.todoApp.todoApp.Model.DTO.RequestObject;

import com.todoApp.todoApp.Model.Entity.Todo;
import com.todoApp.todoApp.Model.Entity.TodoCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoCategoryResponse {
    private Long id;
    private String categoryName;



    public void convertFromCategory(TodoCategory todoCategory){
        this.id=todoCategory.getId();
        this.categoryName= todoCategory.getCategoryName();

    }


}

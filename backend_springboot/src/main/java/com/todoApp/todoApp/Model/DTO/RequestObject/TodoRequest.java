package com.todoApp.todoApp.Model.DTO.RequestObject;

import com.todoApp.todoApp.Model.Entity.Todo;
import com.todoApp.todoApp.Model.Entity.TodoCategory;

public record TodoRequest(String todoName, String todoCategory , String todoDescription, String todoColor, String date, boolean completed) {
// TODO: 27.05.2024 add todo update for completed property
    // TODO: 27.05.2024 todo delete 
    // TODO: 27.05.2024 save access token to local storage 

}

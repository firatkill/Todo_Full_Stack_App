package com.todoApp.todoApp.Model.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

@Table(name = "todo_categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id",name="user_id")
    private User user;

    @Column(unique = true,nullable = false)
    private String categoryName;
}

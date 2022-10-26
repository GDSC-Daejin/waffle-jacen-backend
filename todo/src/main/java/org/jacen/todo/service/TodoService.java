package org.jacen.todo.service;

import org.jacen.todo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface TodoService {

    Optional<Todo> findById(String id);

    // Create
    Todo addTodo(Todo todo);

    // Update
    Todo updateById(String id, Todo todo);

    // Delete
    void deleteById(String id);

    Page<Todo> findByPage(Pageable pageable);
}

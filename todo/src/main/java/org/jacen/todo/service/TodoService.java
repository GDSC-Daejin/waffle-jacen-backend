package org.jacen.todo.service;

import org.jacen.todo.model.Todo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();

    Optional<Todo> findById(String id);

    // Create
    Todo addTodo(Todo todo);

    // Update
    Optional<Todo> updateById(String id, Todo todo);

    // Delete
    void deleteById(String id);
}

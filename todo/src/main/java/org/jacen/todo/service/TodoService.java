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
    Optional<Todo> updateById(String id, Todo todo);

    // Delete
    Optional<Todo> deleteById(String id);

    // Recover
    Optional<Todo> recoverById(String id);

    Page<Todo> findByPage(Pageable pageable);

    Page<Todo> findByDeletedIsTrue(Pageable pageable);
    Page<Todo> findByDeletedIsFalse(Pageable pageable);

    Page<Todo> findByDeletedIsFalseAndCompletedIsFalse(Pageable pageable);
}

package org.jacen.todo.service.impl;

import java.util.Optional;
import org.jacen.todo.model.Todo;
import org.jacen.todo.repository.TodoRepository;
import org.jacen.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository repository;

    @Override
    public Optional<Todo> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        if(todo.getTitle() == null) throw new IllegalArgumentException("Title is required");
        if(todo.getCompleted() == null)
            todo.setCompleted(false);
        if(todo.getContent() == null)
            todo.setContent("");
        return repository.insert(todo);
    }

    @Override
    public Todo updateById(String id, Todo todo) {
        Optional<Todo> todoOptional = repository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todoFromDb = todoOptional.get();
            if(todo.getTitle() != null) {
                todoFromDb.setTitle(todo.getTitle());
            }
            if(todo.getCompleted() != null) {
                todoFromDb.setCompleted(todo.getCompleted());
            }
            if(todo.getContent() != null) {
                todoFromDb.setContent(todo.getContent());
            }
            return repository.save(todoFromDb);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Todo> findByPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Todo> findByDeletedIsTrue(Pageable pageable) {
        return repository.findByDeletedIsTrue(pageable);
    }

    @Override
    public Page<Todo> findByDeletedIsFalse(Pageable pageable) {
        return repository.findByDeletedIsFalse(pageable);
    }

    @Override
    public Page<Todo> findByDeletedIsFalseAndCompletedIsFalse(Pageable pageable) {
        return repository.findByDeletedIsFalseAndCompletedIsFalse(pageable);
    }
}
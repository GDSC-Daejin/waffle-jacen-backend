package org.jacen.todo.service.impl;

import java.util.List;
import java.util.Optional;

import org.jacen.todo.dto.TodoDto;
import org.jacen.todo.model.Todo;
import org.jacen.todo.repository.TodoRepository;
import org.jacen.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository repository;

    @Override
    public List<Todo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Todo> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return repository.insert(todo);
    }

    @Override
    public Optional<Todo> updateById(String id, Todo todo) {
        if (repository.existsById(id)) {
            return Optional.of(repository.save(todo));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
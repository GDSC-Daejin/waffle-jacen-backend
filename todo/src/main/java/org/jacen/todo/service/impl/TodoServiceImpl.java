package org.jacen.todo.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.jacen.todo.model.Todo;
import org.jacen.todo.repository.TodoRepository;
import org.jacen.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Todo> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        if(todo.getTitle() == null) todo.setTitle("");
        if(todo.getContent() == null) todo.setContent("");
        todo.setCreatedDate(LocalDateTime.now());
        todo.setUpdatedDate(LocalDateTime.now());
        todo.setCompleted(false);
        todo.setDeleted(false);
        return repository.insert(todo);
    }

    @Override
    public Optional<Todo> updateById(String id, Todo todo) {
        Optional<Todo> todoOptional = repository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todoFromDb = todoOptional.get();
            if (todoFromDb.getDeleted())
                return Optional.empty();
            if(todo.getTitle() != null) {
                todoFromDb.setTitle(todo.getTitle());
            }
            if(todo.getCompleted() != null) {
                todoFromDb.setCompleted(todo.getCompleted());
            }
            if(todo.getContent() != null) {
                todoFromDb.setContent(todo.getContent());
            }
            todoFromDb.setUpdatedDate(LocalDateTime.now());
            return Optional.of(repository.save(todoFromDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Todo> deleteById(String id) {
        Optional<Todo> todoOptional = repository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todoFromDb = todoOptional.get();
            if (todoFromDb.getDeleted()) {
                repository.deleteById(id);
                return Optional.of(todoFromDb);
            }
            todoFromDb.setDeleted(true);
            todoFromDb.setDeletedDate(LocalDateTime.now());
            return Optional.of(repository.save(todoFromDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Todo> recoverById(String id) {
        Optional<Todo> todoOptional = repository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todoFromDb = todoOptional.get();
            if (todoFromDb.getDeleted()) {
                todoFromDb.setDeleted(false);
                todoFromDb.setDeletedDate(null);
                return Optional.of(repository.save((todoFromDb)));
            }
        }
        return Optional.empty();
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
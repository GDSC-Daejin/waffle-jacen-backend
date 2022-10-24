package org.jacen.todo.controller;

import java.util.List;
import java.util.Optional;

import org.jacen.todo.dto.TodoDto;
import org.jacen.todo.model.Todo;
import org.jacen.todo.service.TodoService;
import org.jacen.todo.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TodoController {

    @Autowired
    private TodoService repository;

    // 투두 리스트를 가져오기
    // TODO : 페이징 구현
    // @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
    @GetMapping("/todo/list")
    public List<TodoDto> list() {
        return ObjectMapperUtils.mapAll(repository.findAll(), TodoDto.class);
    }

    // id로 투두 세부정보 가져오기
    @GetMapping("/todo/{id}")
    public TodoDto getTodoById(@PathVariable String id) {
        Optional<Todo> todo = repository.findById(id);
        return todo.map(value -> ObjectMapperUtils.map(value, TodoDto.class)).orElse(null);
    }

    // 투두 추가
    @PostMapping("/todo")
    public TodoDto postMethodName(@RequestBody Todo entity) {
        return ObjectMapperUtils.map(repository.addTodo(entity), TodoDto.class);
    }

    // 투두 수정
    @PutMapping("/todo/{id}")
    public TodoDto updateTodoById(@PathVariable String id, @RequestBody TodoDto todo) {
        return ObjectMapperUtils.map(repository.updateById(id, ObjectMapperUtils.map(todo, Todo.class)), TodoDto.class);
    }

    // 투두 삭제
    @DeleteMapping("/todo/{id}")
    public String deleteTodoById(@PathVariable String id) {
        repository.deleteById(id);
        return "Deleted";
    }
}

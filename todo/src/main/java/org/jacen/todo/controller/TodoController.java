package org.jacen.todo.controller;

import java.util.List;
import java.util.Optional;

import org.jacen.todo.dto.PagedTodoDto;
import org.jacen.todo.dto.TodoDto;
import org.jacen.todo.model.Todo;
import org.jacen.todo.service.TodoService;
import org.jacen.todo.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

class APIResponse<T> {
    private Boolean success;
    private T data;

    public APIResponse(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

@RestController
public class TodoController {

    private final TodoService repository;

    public TodoController(TodoService repository) {
        this.repository = repository;
    }

    // 투두 리스트를 가져오기
    @GetMapping("/todo/list")
    public APIResponse list(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return new APIResponse(true, new PagedTodoDto(repository.findByDeletedIsFalse(pageable)));
    }

    // id로 투두 세부정보 가져오기
    @GetMapping("/todo/{id}")
    public APIResponse getTodoById(@PathVariable String id) {
        Optional<Todo> todo = repository.findById(id);
        return todo.map(value -> new APIResponse(true, ObjectMapperUtils.map(value, TodoDto.class)))
                .orElseGet(() -> new APIResponse(false, null));
    }

    // 투두 추가
    @PostMapping("/todo")
    public APIResponse<Object> postMethodName(@RequestBody TodoDto todo) {
        Todo todoEntity = ObjectMapperUtils.map(todo, Todo.class);
        System.out.println(todoEntity);
        return new APIResponse<>(true, ObjectMapperUtils.map(repository.addTodo(todoEntity), TodoDto.class));
    }

    // 투두 수정
    @PutMapping("/todo/{id}")
    public APIResponse updateTodoById(@PathVariable String id, @RequestBody TodoDto todo) {
        try {
            todo.setId(id);
            Todo entity = ObjectMapperUtils.map(todo, Todo.class);
            System.out.println(entity);
            return new APIResponse(true, ObjectMapperUtils.map(repository.updateById(id, entity), TodoDto.class));
        } catch (Exception e) {
            return new APIResponse(false, null);
        }
    }

    // 투두 삭제
    @DeleteMapping("/todo/{id}")
    public APIResponse deleteTodoById(@PathVariable String id) {
        try {
            repository.deleteById(id);
            return new APIResponse(true, null);
        } catch (Exception e) {
            System.out.println(e);
            return new APIResponse(false, null);
        }
    }
}

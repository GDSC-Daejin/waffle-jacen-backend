package org.jacen.todo.dto;

import org.jacen.todo.model.Todo;
import org.jacen.todo.utils.ObjectMapperUtils;
import org.springframework.data.domain.Page;

import java.util.List;

class Paging {
    private Integer total_pages;
    private Integer current_page;
    private Boolean is_last_page;

    public Paging(Integer total_pages, Integer current_page, Boolean is_last_page) {
        this.total_pages = total_pages;
        this.current_page = current_page;
        this.is_last_page = is_last_page;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public Boolean getIs_last_page() {
        return is_last_page;
    }
}

public class PagedTodoDto {
    private final List<TodoDto> todos;
    private final Paging paging;

    public PagedTodoDto(Page<Todo> todos) {
        this.todos = ObjectMapperUtils.mapAll(todos.getContent(), TodoDto.class);
        this.paging = new Paging(todos.getTotalPages(), todos.getNumber(), todos.isLast());
    }

    public List<TodoDto> getTodos() {
        return todos;
    }

    public Paging getPaging() {
        return paging;
    }
}
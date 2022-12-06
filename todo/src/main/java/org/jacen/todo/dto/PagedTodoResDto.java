package org.jacen.todo.dto;

import org.jacen.todo.model.Todo;
import org.jacen.todo.utils.ObjectMapperUtils;
import org.springframework.data.domain.Page;

import java.util.List;

class PagingRes {
    private final Integer total_pages;
    private final Integer current_page;
    private final Boolean is_last_page;

    public PagingRes(Integer total_pages, Integer current_page, Boolean is_last_page) {
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

public class PagedTodoResDto {
    private final List<TodoResDto> todos;
    private final PagingRes pagingRes;

    public PagedTodoResDto(Page<Todo> todos) {
        this.todos = ObjectMapperUtils.mapAll(todos.getContent(), TodoResDto.class);
        this.pagingRes = new PagingRes(todos.getTotalPages(), todos.getNumber(), todos.isLast());
    }

    public List<TodoResDto> getTodos() {
        return todos;
    }

    public PagingRes getPaging() {
        return pagingRes;
    }
}
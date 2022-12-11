package org.jacen.todo.dto;


import javax.validation.constraints.NotNull;

public class CreateTodoReqDto {

    @NotNull(message = "title cannot be null")
    private String title;
    private String content;

    public CreateTodoReqDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CreateTodoReqDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "CreateTodoDto(title=" + this.getTitle() + ", content=" + this.getContent() + ")";
    }
}

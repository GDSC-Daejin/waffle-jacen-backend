package org.jacen.todo.dto;

public class UpdateTodoReqDto {
    private String title;
    private String content;
    private Boolean completed;

    public UpdateTodoReqDto(String title, String content, Boolean completed) {
        this.title = title;
        this.content = content;
        this.completed = completed;
    }

    public UpdateTodoReqDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String toString() {
        return "UpdateTodoDto(title=" + this.getTitle() + ", content=" + this.getContent() + ", completed=" + this.getCompleted() + ")";
    }
}

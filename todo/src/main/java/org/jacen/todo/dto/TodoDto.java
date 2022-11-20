package org.jacen.todo.dto;

import java.time.LocalDateTime;

public class TodoDto {

  private String id;
  private String title;
  private String content;
  private Boolean completed;

  private Boolean deleted;

  private LocalDateTime createdDate;

  private LocalDateTime updatedDate;

  public TodoDto() {}

  public TodoDto(String id, String title, String content, Boolean completed, Boolean deleted, LocalDateTime createdDate, LocalDateTime updatedDate) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.completed = completed;
    this.deleted = deleted;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }

  public Boolean getCompleted() {
    return completed;
  }
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Boolean getDeleted() {
    return deleted;
  }
}

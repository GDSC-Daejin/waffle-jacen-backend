package org.jacen.todo.dto;

import java.time.LocalDateTime;

public class TodoResDto {

  private String id;
  private String title;
  private String content;
  private Boolean completed;

  private Boolean deleted;

  private LocalDateTime createdDate;

  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;

  public TodoResDto(String id, String title, String content, Boolean completed, Boolean deleted, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.completed = completed;
    this.deleted = deleted;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.deletedDate = deletedDate;
  }

  public TodoResDto() {
  }

  public Boolean getCompleted() {
    return completed;
  }

  public String getContent() {
    return content;
  }

  public String getTitle() {
    return title;
  }
  public String getId() {
    return id;
  }
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }
  public Boolean getDeleted() {
    return deleted;
  }
  public LocalDateTime getDeletedDate() {
    return deletedDate;
  }

  public void setId(String id) {
    this.id = id;
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

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public void setDeletedDate(LocalDateTime deletedDate) {
    this.deletedDate = deletedDate;
  }
}

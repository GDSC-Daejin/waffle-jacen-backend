package org.jacen.todo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "todo")
public class Todo {
	private String id;
	private String title;
	private String content;
	private Boolean completed;

	@Version
	private Integer todo_version;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime updatedDate;

	public Todo() {}
	public Todo(String title, String content, Boolean completed, Integer todo_version, LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.title = title;
		this.content = content;
		this.completed = completed;
		this.todo_version = todo_version;
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
	public Integer getTodo_version() {
		return todo_version;
	}
	public void setTodo_version(Integer todo_version) {
		this.todo_version = todo_version;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", content=" + content + ", completed=" + completed
				+ ", todo_version=" + todo_version + "]";
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
}

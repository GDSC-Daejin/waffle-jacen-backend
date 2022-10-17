package org.jacen.todo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todo")
public class Todo {

	private String id;
	private String title;
	private String content;
	private Boolean completed;
	private Integer todo_version;

	public Todo() {}
	public Todo(String title, String content, Boolean completed, Integer todo_version) {
		this.title = title;
		this.content = content;
		this.completed = completed;
		this.todo_version = todo_version;
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
}

package org.jacen.todo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "todo")
public class Todo {
	@Id
	private String id;
	private String title;

	private String content;
	private Boolean completed;

	private Boolean deleted;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime updatedDate;

	private LocalDateTime deletedDate;

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", content=" + content + ", completed=" + completed
				 + "]";
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

    public void setDeleted(boolean deleted) {
		this.deleted = deleted;
    }

	public void setDeletedDate(LocalDateTime deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getId() {
		return this.id;
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

	public Boolean getDeleted() {
		return this.deleted;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public LocalDateTime getDeletedDate() {
		return this.deletedDate;
	}
}

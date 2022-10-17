package org.jacen.todo.repository;

import java.util.List;

import org.jacen.todo.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
}

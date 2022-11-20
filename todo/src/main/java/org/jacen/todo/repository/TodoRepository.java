package org.jacen.todo.repository;

import org.jacen.todo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
    Page<Todo> findByCompletedIsTrue(Pageable pageable);
    Page<Todo> findByCompletedIsFalse(Pageable pageable);

    Page<Todo> findByDeletedIsTrue(Pageable pageable);
    Page<Todo> findByDeletedIsFalse(Pageable pageable);
    Page<Todo> findByDeletedIsFalseAndCompletedIsFalse(Pageable pageable);
}

package org.jacen.todo.controller;

import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jacen.todo.dto.CreateTodoReqDto;
import org.jacen.todo.dto.PagedTodoResDto;
import org.jacen.todo.dto.TodoResDto;
import org.jacen.todo.dto.UpdateTodoReqDto;
import org.jacen.todo.model.Todo;
import org.jacen.todo.service.TodoService;
import org.jacen.todo.types.APIResponse;
import org.jacen.todo.utils.ObjectMapperUtils;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/*
    method  |  url                  |  request body             |  description
    GET     /todo?page=0&size=10                                get todo list
    POST    /todo                   title, content              create todo
    PUT     /todo/{id}              title, content, completed   update todo
    DELETE  /todo/{id}                                          delete todo
    PUT     /todo/recover/{id}                                  recover todo
    GET     /todo/deleted?page=0&size=10                        get deleted todo list
    GET     /todo/completed?page=0&size=10                      get completed todo list
 */


@Tag(name = "Todo", description = "Todo API")
@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    private final TodoService repository;

    public TodoController(TodoService repository) {
        this.repository = repository;
    }

    // 투두 리스트를 가져오기
    @GetMapping("")
    @Operation(summary = "todo list 가져오기", description = "삭제되지 않은 todo list를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    public ResponseEntity<APIResponse<PagedTodoResDto>> list(@ParameterObject @PageableDefault(size = 10, sort = { "createdDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
        PagedTodoResDto pagedTodos = new PagedTodoResDto(repository.findByDeletedIsFalse(pageable));
        return ResponseEntity.ok(new APIResponse<>(true, pagedTodos));
    }

    // 삭제된 투두 리스트를 가져오기 (휴지통)
    @GetMapping("/trash")
    @Operation(summary = "삭제된 todo list 가져오기", description = "임시로 삭제된 todo list를 가져옵니다. (deleted가 true인 todo list)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
    })
    public ResponseEntity<APIResponse<PagedTodoResDto>> trash(@ParameterObject @PageableDefault(size = 10, sort = { "createdDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(new APIResponse<>(true, new PagedTodoResDto(repository.findByDeletedIsTrue(pageable))));
    }

    // id로 투두 세부정보 가져오기
    @GetMapping("/{id}")
    @Operation(summary = "todo 세부정보 가져오기", description = "id로 todo 세부정보를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 id와 일치하는 todo가 없음"),
    })
    public ResponseEntity<APIResponse<TodoResDto>> getTodoById(@PathVariable String id) {
        Optional<Todo> todo = repository.findById(id);
        return todo.map(value ->
                        ResponseEntity.ok(new APIResponse<>(true, ObjectMapperUtils.map(value, TodoResDto.class)))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(false, null))
                );
    }

    // 투두 추가
    @PostMapping("")
    @Operation(summary = "새 todo 추가하기", description = "새 todo를 추가합니다.")
    public ResponseEntity<APIResponse<TodoResDto>> addTodo(@Valid @RequestBody CreateTodoReqDto todo) {
        Todo newTodo = repository.addTodo(ObjectMapperUtils.map(todo, Todo.class));
        return ResponseEntity.ok(new APIResponse<>(true, ObjectMapperUtils.map(newTodo, TodoResDto.class)));
    }

    // 투두 수정
    @PutMapping("/{id}")
    @Operation(summary = "기존 todo 수정하기", description = "id로 기존 todo를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 id와 일치하는 todo가 없음"),
    })
    public ResponseEntity<APIResponse<TodoResDto>> updateTodoById(@PathVariable String id, @RequestBody @Valid UpdateTodoReqDto todo) {
        Todo entity = ObjectMapperUtils.map(todo, Todo.class);
        entity.setId(id);
        System.out.println(entity);
        Optional<Todo> updatedTodo = repository.updateById(id, entity);
        return updatedTodo.map(value ->
                        ResponseEntity.ok(new APIResponse<>(true, ObjectMapperUtils.map(value, TodoResDto.class)))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(false, null))
                );
    }

    // 투두 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "todo 삭제하기", description = "id로 todo를 삭제합니다. deleted가 false인 경우, true로 변경합니다. true인 경우, 완전히 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 id와 일치하는 todo가 없음"),
    })
    public ResponseEntity<APIResponse<TodoResDto>> deleteTodoById(@PathVariable String id) {
        Optional<Todo> deletedTodo = repository.deleteById(id);
        return deletedTodo.map(value ->
                        ResponseEntity.ok(new APIResponse<>(true, ObjectMapperUtils.map(value, TodoResDto.class)))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(false, null))
                );
    }

    // 투두 삭제취소
    @PutMapping("/recover/{id}")
    @Operation(summary = "todo 삭제취소하기", description = "id로 todo를 삭제를 취소합니다. deleted가 true인 경우, false로 변경합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 id와 일치하는 todo가 없음"),
    })
    public ResponseEntity<APIResponse<TodoResDto>> recoverTodoById(@PathVariable String id) {
        Optional<Todo> todo = repository.recoverById(id);
        return todo.map(value ->
                        ResponseEntity.ok().body(new APIResponse<>(true, ObjectMapperUtils.map(value, TodoResDto.class)))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(false, null))
                );
    }
}

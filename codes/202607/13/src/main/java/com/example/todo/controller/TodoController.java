package com.example.todo.controller;

import com.example.todo.model.TodoItem;
import com.example.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 할 일(Todo) 항목에 대한 REST API 요청을 처리하는 컨트롤러입니다.
 * 모든 API 경로는 "/api/todos"로 시작합니다.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    /**
     * TodoController의 생성자입니다.
     * Spring의 의존성 주입(Dependency Injection)을 통해 TodoRepository를 주입받습니다.
     * @param todoRepository 할 일 항목 데이터에 접근하기 위한 저장소
     */
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * 모든 할 일 항목을 조회합니다.
     * GET /api/todos
     * @return 모든 할 일 항목의 리스트
     */
    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    /**
     * 특정 ID를 가진 할 일 항목을 조회합니다.
     * GET /api/todos/{id}
     * @param id 조회할 할 일 항목의 ID
     * @return 해당 ID를 가진 할 일 항목 (HTTP 200 OK) 또는 찾을 수 없는 경우 (HTTP 404 NOT FOUND)
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(todoItem -> new ResponseEntity<>(todoItem, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 새로운 할 일 항목을 생성합니다.
     * POST /api/todos
     * @param todoItem 생성할 할 일 항목 데이터
     * @return 생성된 할 일 항목 (HTTP 201 CREATED)
     */
    @PostMapping
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem todoItem) {
        TodoItem savedTodo = todoRepository.save(todoItem);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    /**
     * 기존 할 일 항목을 업데이트합니다.
     * PUT /api/todos/{id}
     * @param id 업데이트할 할 일 항목의 ID
     * @param todoItem 업데이트할 할 일 항목 데이터
     * @return 업데이트된 할 일 항목 (HTTP 200 OK) 또는 찾을 수 없는 경우 (HTTP 404 NOT FOUND)
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        return todoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(todoItem.getTitle());
                    existingTodo.setCompleted(todoItem.isCompleted());
                    todoRepository.save(existingTodo);
                    return new ResponseEntity<>(existingTodo, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 특정 ID를 가진 할 일 항목을 삭제합니다.
     * DELETE /api/todos/{id}
     * @param id 삭제할 할 일 항목의 ID
     * @return 성공적인 삭제 응답 (HTTP 204 NO CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

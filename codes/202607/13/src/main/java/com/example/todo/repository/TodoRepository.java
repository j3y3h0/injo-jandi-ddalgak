package com.example.todo.repository;

import com.example.todo.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 할 일(Todo) 항목을 관리하는 인메모리 저장소입니다.
 * 실제 데이터베이스 대신 애플리케이션 메모리에서 데이터를 유지합니다.
 */
@Repository
public class TodoRepository {

    private final List<TodoItem> todoItems = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    /**
     * 모든 할 일 항목을 반환합니다.
     * @return 모든 할 일 항목의 리스트
     */
    public List<TodoItem> findAll() {
        return new ArrayList<>(todoItems);
    }

    /**
     * ID를 통해 할 일 항목을 찾습니다.
     * @param id 찾을 할 일 항목의 ID
     * @return 해당 ID를 가진 할 일 항목 (존재하지 않으면 Optional.empty())
     */
    public Optional<TodoItem> findById(Long id) {
        return todoItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    /**
     * 할 일 항목을 저장하거나 업데이트합니다.
     * 새 항목이면 ID를 할당하고 추가하며, 기존 항목이면 업데이트합니다.
     * @param todoItem 저장하거나 업데이트할 할 일 항목
     * @return 저장되거나 업데이트된 할 일 항목
     */
    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setId(counter.incrementAndGet());
            todoItems.add(todoItem);
        } else {
            findById(todoItem.getId()).ifPresent(existingItem -> {
                existingItem.setTitle(todoItem.getTitle());
                existingItem.setCompleted(todoItem.isCompleted());
            });
        }
        return todoItem;
    }

    /**
     * ID를 통해 할 일 항목을 삭제합니다.
     * @param id 삭제할 할 일 항목의 ID
     */
    public void deleteById(Long id) {
        todoItems.removeIf(item -> item.getId().equals(id));
    }
}

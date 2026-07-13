package com.example.todo.model;

import java.util.Objects;

/**
 * 할 일(Todo) 항목을 나타내는 모델 클래스입니다.
 */
public class TodoItem {

    private Long id;
    private String title;
    private boolean completed;

    // 기본 생성자
    public TodoItem() {
    }

    // 모든 필드를 포함하는 생성자
    public TodoItem(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // Getter 및 Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // toString, equals, hashCode 메서드 오버라이드
    @Override
    public String toString() {
        return "TodoItem{" +
               "id=" + id +
               ", title='" + title + ''' +
               ", completed=" + completed +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return completed == todoItem.completed &&
               Objects.equals(id, todoItem.id) &&
               Objects.equals(title, todoItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, completed);
    }
}

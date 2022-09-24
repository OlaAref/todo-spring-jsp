package com.olaaref.todo.service;

import java.time.LocalDate;
import java.util.List;

import com.olaaref.todo.entity.Todo;

public interface TodoService {
	
	public Todo getTodo(long id);
	public List<Todo> getAllTodos();
	public List<Todo> getByCategory(long categoryId);
	public void saveTodo(Todo todo);
	public void deleteTodo(long id);
	public String updateCompletedStatus(long id, boolean status);
	public List<Todo> getByDueDate(LocalDate dueDate);
	public int getTodaySize();
	public int getAllSize();
	public List<Todo> getCompletedTodos();
}

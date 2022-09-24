package com.olaaref.todo.dao;

import java.time.LocalDate;
import java.util.List;

import com.olaaref.todo.entity.Todo;

public interface TodoDAO {

	public Todo getTodo(long id);
	public List<Todo> getAllTodos();
	public List<Todo> getByCategory(long categoryId);
	public void saveTodo(Todo todo);
	public void updateTodo(Todo todo);
	public void deleteTodo(Todo todo);
	public int updateCompletedStatus(long id, boolean status);
	public List<Todo> getByDueDate(LocalDate dueDate);
	public List<Todo> getCompletedTodos();
	
}

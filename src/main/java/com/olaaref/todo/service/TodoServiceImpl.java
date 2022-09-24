package com.olaaref.todo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaaref.todo.dao.CategoryDAO;
import com.olaaref.todo.dao.TodoDAO;
import com.olaaref.todo.entity.Category;
import com.olaaref.todo.entity.Todo;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoDAO todoDao;

	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public Todo getTodo(long id) {
		return todoDao.getTodo(id);
	}

	@Override
	public List<Todo> getAllTodos() {
		return todoDao.getAllTodos();
	}

	@Override
	public List<Todo> getByCategory(long categoryId) {
		return todoDao.getByCategory(categoryId);
	}

	@Override
	public void saveTodo(Todo todo) {
		if(todo.getId() == 0) {
			Category category = categoryDao.getCategory(todo.getCategory().getId());
			todo.setCategory(category);
			todo.setCompleted(false);
			todoDao.saveTodo(todo);
		}
		else {
			todoDao.updateTodo(todo);
		}
		
	}

	@Override
	public void deleteTodo(long id) {
		Todo todo = getTodo(id);
		todoDao.deleteTodo(todo);
	}

	@Override
	public String updateCompletedStatus(long id, boolean status) {
		int affectedRows = todoDao.updateCompletedStatus(id, status);
		if(affectedRows > 0) {
			return "Completed status changed successfully.";
		}
		else {
			return "This task does not exist.";
		}
	}
	
	@Override
	public List<Todo> getByDueDate(LocalDate dueDate){
		return todoDao.getByDueDate(dueDate);
	}
	
	@Override
	public List<Todo> getCompletedTodos(){
		return todoDao.getCompletedTodos();
	}

	@Override
	public int getTodaySize() {
		return getByDueDate(LocalDate.now()).size();
	}

	@Override
	public int getAllSize() {
		return getAllTodos().size();
	}

}



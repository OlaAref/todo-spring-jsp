package com.olaaref.todo.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olaaref.todo.entity.Todo;

@Repository
public class TodoDAOImp implements TodoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Todo getTodo(long id) {
		
		Session session = sessionFactory.getCurrentSession();
		Todo todo = session.get(Todo.class, id);
		
		return todo;
	}

	@Override
	public List<Todo> getAllTodos() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Todo");
		List<Todo> todos = query.getResultList();
		
		return todos;
	}

	@Override
	public void saveTodo(Todo todo) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(todo);
	}
	
	@Override
	public void updateTodo(Todo todo) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(todo);
	}

	@Override
	public void deleteTodo(Todo todo) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(todo);
	}

	@Override
	public List<Todo> getByCategory(long categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select t from Todo t where t.category.id = ?1");
		query.setParameter(1, categoryId);
		List<Todo> todos = query.getResultList();

		return todos;
	}

	@Override
	public int updateCompletedStatus(long id, boolean status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Todo t set t.completed = ?1 where t.id = ?2");
		query.setParameter(1, status);
		query.setParameter(2, id);
		int affectedRows = query.executeUpdate();
		return affectedRows;
	}
	
	@Override
	public List<Todo> getByDueDate(LocalDate dueDate){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select t from Todo t where t.dueDate = ?1");
		query.setParameter(1, dueDate);
		return query.getResultList();
	}

	@Override
	public List<Todo> getCompletedTodos() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select t from Todo t where t.completed = true");
		return query.getResultList();
	}

}

package com.olaaref.todo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olaaref.todo.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Category getCategory(long id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);

		return category;
	}

	@Override
	public List<Category> getAllCategories() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category");
		List<Category> categories = query.getResultList();

		return categories;
	}

	@Override
	public void saveCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(category);

	}

	@Override
	public void deleteCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(category);

	}

	@Override
	public void updateCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(category);
		
	}

}

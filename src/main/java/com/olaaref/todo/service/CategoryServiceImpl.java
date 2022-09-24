package com.olaaref.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olaaref.todo.dao.CategoryDAO;
import com.olaaref.todo.dao.TodoDAO;
import com.olaaref.todo.entity.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private TodoDAO todoDao;

	@Override
	public Category getCategory(long id) {
		return categoryDao.getCategory(id);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	public void saveCategory(Category category) {
		if(category.getId() == 0) {
			categoryDao.saveCategory(category);
		}
		else {
			categoryDao.updateCategory(category);
		}
	}

	@Override
	public void deleteCategory(long id) {
		Category category = getCategory(id);
		categoryDao.deleteCategory(category);
	}

}

package com.olaaref.todo.dao;

import java.util.List;

import com.olaaref.todo.entity.Category;


public interface CategoryDAO {

	public Category getCategory(long id);
	public List<Category> getAllCategories();
	public void saveCategory(Category category);
	public void updateCategory(Category category);
	public void deleteCategory(Category category);
}

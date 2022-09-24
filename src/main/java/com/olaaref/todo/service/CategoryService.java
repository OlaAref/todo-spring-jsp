package com.olaaref.todo.service;

import java.util.List;

import com.olaaref.todo.entity.Category;

public interface CategoryService {
	public Category getCategory(long id);
	public List<Category> getAllCategories();
	public void saveCategory(Category category);
	public void deleteCategory(long id);
}

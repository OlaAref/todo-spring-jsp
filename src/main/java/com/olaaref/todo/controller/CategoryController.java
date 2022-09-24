package com.olaaref.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olaaref.todo.entity.Category;
import com.olaaref.todo.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/new")
	public String newCategoryForm(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("title", "Add");
		return "category/category-form";
	}
	
	@PostMapping("/save")
	public String saveCategory(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/category/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editCategoryForm(@PathVariable("id") long id, Model model) {
		Category category = categoryService.getCategory(id);
		model.addAttribute("category", category);
		model.addAttribute("title", "Edit");
		return "category/category-form";
	}
	
	@GetMapping("/list")
	public String listCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "category/list-categories";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategoryForm(@PathVariable("id") long id, Model model) {
		categoryService.deleteCategory(id);
		return "redirect:/category/list";
	}
}

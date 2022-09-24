package com.olaaref.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.olaaref.todo.entity.Todo;
import com.olaaref.todo.service.CategoryService;
import com.olaaref.todo.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/new")
	public String newTodoForm(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("todo", new Todo());
		model.addAttribute("title", "Add");
		return "todo/todo-form";
	}
	
	@PostMapping("/save")
	public String saveTodo(@ModelAttribute("todo") Todo todo) {
		System.out.println(todo);
		todoService.saveTodo(todo);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editTodoForm(@PathVariable("id") long id,
								Model model) {
		Todo todo = todoService.getTodo(id);
		model.addAttribute("todo", todo);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("title", "Edit");
		return "todo/todo-form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTodo(@PathVariable("id") long id) {
		todoService.deleteTodo(id);
		return "redirect:/";
	}
	
}















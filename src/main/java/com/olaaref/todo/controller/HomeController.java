package com.olaaref.todo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.olaaref.todo.service.CategoryService;
import com.olaaref.todo.service.TodoService;

@Controller
public class HomeController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String mainPage(Model model) {
		model.addAttribute("todos", todoService.getAllTodos());
		model.addAttribute("todaySize", todoService.getTodaySize());
		model.addAttribute("allSize", todoService.getAllSize());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "index";
	}
	
	@GetMapping("/today")
	public String todayTodos(Model model) {
		model.addAttribute("todos", todoService.getByDueDate(LocalDate.now()));
		model.addAttribute("todaySize", todoService.getTodaySize());
		model.addAttribute("allSize", todoService.getAllSize());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "index";
	}
	
	@GetMapping("/completed")
	public String completedTodos(Model model) {
		model.addAttribute("todos", todoService.getCompletedTodos());
		model.addAttribute("todaySize", todoService.getTodaySize());
		model.addAttribute("allSize", todoService.getAllSize());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "index";
	}
	
	@GetMapping("/byCategory/{id}")
	public String completedTodos(@PathVariable("id") long id, Model model) {
		model.addAttribute("todos", todoService.getByCategory(id));
		model.addAttribute("todaySize", todoService.getTodaySize());
		model.addAttribute("allSize", todoService.getAllSize());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String accessDeniedPage() {
		return "access-denied";
	}
	
}












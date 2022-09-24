package com.olaaref.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olaaref.todo.entity.User;
import com.olaaref.todo.service.RoleService;
import com.olaaref.todo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/new")
	public String newUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("title", "Add");
		return "user/user-form";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editUserForm(@PathVariable("id") long id, Model model) {
		User user = userService.getById(id);
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("title", "Edit");
		return "user/user-form";
	}
	
	@GetMapping("/list")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "user/list-users";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUserForm(@PathVariable("id") long id, Model model) {
		userService.deleteUser(id);
		return "redirect:/user/list";
	}
}

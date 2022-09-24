package com.olaaref.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olaaref.todo.entity.Role;
import com.olaaref.todo.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/new")
	public String newRoleForm(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("title", "Add");
		return "role/role-form";
	}
	
	@PostMapping("/save")
	public String saveRole(@ModelAttribute("role") Role role) {
		roleService.saveRole(role);
		return "redirect:/role/list";
	}
	
	@GetMapping("/list")
	public String listRoles(Model model) {
		model.addAttribute("roles", roleService.getAllRoles());
		return "role/list-roles";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRoleForm(@PathVariable("id") long id, Model model) {
		roleService.deleteRole(id);
		return "redirect:/role/list";
	}
}

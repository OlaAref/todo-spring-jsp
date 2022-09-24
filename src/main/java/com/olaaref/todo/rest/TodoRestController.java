package com.olaaref.todo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.olaaref.todo.service.TodoService;

@RestController
public class TodoRestController {
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/{id}/completed/{status}")
	public String changeCompleteStatus(@PathVariable("status") boolean status,
									   @PathVariable("id") long id) {
		return todoService.updateCompletedStatus(id, status);
	}
}

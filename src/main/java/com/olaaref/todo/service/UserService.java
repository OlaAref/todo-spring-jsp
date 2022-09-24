package com.olaaref.todo.service;

import java.util.List;

import com.olaaref.todo.entity.User;

public interface UserService {

	public User getById(long id);
	public User getByEmail(String email);
	public List<User> getAllUsers();
	public void saveUser(User user);
	public void deleteUser(long id);
}

package com.olaaref.todo.dao;

import java.util.List;

import com.olaaref.todo.entity.User;

public interface UserDAO {

	public User getById(long id);
	public User getByEmail(String email);
	public List<User> getAllUsers();
	public void saveUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
}

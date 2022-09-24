package com.olaaref.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.olaaref.todo.dao.UserDAO;
import com.olaaref.todo.entity.User;

@Service
public class TodoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User user = userDao.getByEmail(userEmail);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid email or password");
		}
		
		return new TodoUserDetails(user);
	}

}

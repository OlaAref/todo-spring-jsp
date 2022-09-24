package com.olaaref.todo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olaaref.todo.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role getRole(long id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, id);
		return role;
	}

	@Override
	public List<Role> getAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role");
		return query.getResultList();
	}

	@Override
	public void saveRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Override
	public void deleteRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(role);
	}

}

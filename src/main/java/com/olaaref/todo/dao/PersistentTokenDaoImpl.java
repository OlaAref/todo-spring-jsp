package com.olaaref.todo.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olaaref.todo.entity.LoginsPersistent;


@Repository("persistentTokenRepository")
@Transactional
public class PersistentTokenDaoImpl implements PersistentTokenRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		
		LoginsPersistent login = new LoginsPersistent();
		login.setSeries(token.getSeries());
		login.setUsername(token.getUsername());
		login.setToken(token.getTokenValue());
		login.setLastUsed(token.getDate());
		
		Session session = sessionFactory.getCurrentSession();
		session.save(login);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		Session session = sessionFactory.getCurrentSession();
		LoginsPersistent login = session.get(LoginsPersistent.class, series);
		login.setToken(tokenValue);
		login.setLastUsed(lastUsed);
		session.update(login);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		Session session = sessionFactory.getCurrentSession();
		LoginsPersistent login = session.get(LoginsPersistent.class, seriesId);
		
		if(login != null) {
			return new PersistentRememberMeToken(login.getUsername(), seriesId, login.getToken(), login.getLastUsed());
		}
		else {
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		session
			.createQuery("delete from LoginsPersistent where username = :name")
			.setParameter("name", username)
			.executeUpdate();
		
	}

}

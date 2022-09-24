package com.olaaref.todo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "logins_persistent")
public class LoginsPersistent {
	@Id
	@Column(name = "series", length = 64, nullable = false)
	private String series;
	
	@Column(name = "username", length = 64, nullable = false)
	private String username;
	
	@Column(name = "token", length = 64, nullable = false)
	private String token;
	
	@Column(name = "last_used", nullable = false)
	private Date lastUsed;
}

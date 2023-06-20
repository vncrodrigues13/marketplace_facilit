package com.marketplace.facilit.forms;

import java.util.Date;

public class UserForm  {

	private long id;

	private String name;

	private String email;

	private String password;

	private Date birthDate;

	public UserForm() {
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public Date getBirthDate() {

		return birthDate;
	}

	public void setBirthDate(Date birthDate) {

		this.birthDate = birthDate;
	}

}

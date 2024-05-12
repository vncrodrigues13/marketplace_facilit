package com.marketplace.facilit.models.user;

import java.util.Date;

public interface User {

	public long getId();

	public void setId(long id);

	public String getName();

	public void setName(String name);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);

	public Date getBirthDate();

	public void setBirthDate(Date birthDate);
}

package com.marketplace.facilit.models.user;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
public class UserImpl implements User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Date birthDate;

	public UserImpl(long id, String name, String email, String password, Date birthDate) {

		this(name, email, password, birthDate);
		setId(id);
	}

	public UserImpl(String name, String email, String password, Date birthDate) {

		setName(name);
		setEmail(email);
		setPassword(password);
		setBirthDate(birthDate);
	}

	public UserImpl() {

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

	@Override
	public boolean equals(Object o) {
		if (o instanceof UserImpl) {
			UserImpl user = (UserImpl) o;

			return user.id == this.id &&
					user.email.equals(this.email);

		}
		return false;
	}
	
}

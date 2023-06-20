package com.marketplace.facilit.services.user;

import com.marketplace.facilit.forms.UserForm;
import com.marketplace.facilit.models.user.UserImpl;

import java.util.List;

public interface IUserService {


	UserImpl addUser(UserForm userForm);

	UserImpl updateUser(UserForm userForm);

	UserImpl getUser(long id);

	UserImpl getUserByEmail(long email);

	List<UserImpl> getUsers();





}

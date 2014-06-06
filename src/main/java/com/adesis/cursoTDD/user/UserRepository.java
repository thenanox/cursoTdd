package com.adesis.cursoTDD.user;

import java.util.List;

public interface UserRepository {
	public List<User> findUsersByPlace(String place);
	public void createNewUser(User user);
}

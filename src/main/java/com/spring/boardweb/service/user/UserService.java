package com.spring.boardweb.service.user;

import javax.servlet.http.HttpSession;

import com.spring.boardweb.entity.User;

public interface UserService {
	void join(User user);
	
	public User idCheck(String userId);
	
	public User getMypage(String userId);
	
	void updateMypage(User user);
	
	
}

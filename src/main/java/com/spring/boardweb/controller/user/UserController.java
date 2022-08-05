package com.spring.boardweb.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boardweb.entity.User;
import com.spring.boardweb.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/join")
	public ModelAndView joinView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/join.html");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView loginView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/login.html");
		return mv;
	}
	
	@PostMapping("/join")
	public ModelAndView join(User user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/login.html");
		
		user.setUserBirth(user.getUserBirthYear() + user.getUserBirthMonth() + user.getUserBirthDay());
		userService.join(user);
		return mv;
	}
	
	@PostMapping("/idCheck")
	public String idCheck(User user) {
		User idCheck = userService.idCheck(user.getUserId());
		
		if(idCheck == null) {
			return "idOk";
		} else {
			return "idFail";
		}
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session) {
		User loginUser = userService.idCheck(user.getUserId());
		
		if(loginUser == null) {
			return "idFail";
		} else {
			if(!loginUser.getUserPw().equals(user.getUserPw())) {
				return "pwFail";
			} else {
				session.setAttribute("loginUser", loginUser);
				return "loginSuccess";
			}
		}	
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {	
//		session.setAttribute("loginUser", null);
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/login.html");
		return mv;
	}
	
	
	@PostMapping("/updateMypage")
	public void updateMypage(User user,
							HttpServletResponse response,
							HttpServletRequest request,
							HttpSession session) throws IOException{
		userService.updateMypage(user);
		
		User loginUser = userService.idCheck(user.getUserId());
		
		session.setAttribute("loginUser", loginUser);

		response.sendRedirect("/mypage/getMypage");
	}
}

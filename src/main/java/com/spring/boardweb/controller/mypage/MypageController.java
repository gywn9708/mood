package com.spring.boardweb.controller.mypage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/mypage")
public class MypageController {

	@GetMapping("/getMypage")
	public ModelAndView getMypageView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mypage/getMypage.html");
		return mv;
	}
	
}

package com.spring.boardweb.controller.list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/list")
public class ListController {
	
	@GetMapping("/getList")
	public ModelAndView getListView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("list/getList.html");
		return mv;
	}

}

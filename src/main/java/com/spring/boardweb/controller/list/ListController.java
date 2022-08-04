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
	//object로 API연동 가능하게 수정했습니다.
	//그리고 집에 가고싶어요~~

	//

	//우빈수
	//네 네네네

}

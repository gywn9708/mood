package com.spring.boardweb.controller.contents;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boardweb.controller.api.KobisSearchMovieInfoApi;
import com.spring.boardweb.controller.api.KobisSearchMovieListApi;
import com.spring.boardweb.entity.Contents;
import com.spring.boardweb.service.contents.ContentService;

@RestController
@RequestMapping("/contents")
public class ContentController {
	
	@Autowired
	ContentService contentService;
	
	@GetMapping("/getContent/{contentTitle}")
	public ModelAndView getContentView(@PathVariable String contentTitle) throws ParseException {

		KobisSearchMovieListApi movieListApi = new KobisSearchMovieListApi();
		KobisSearchMovieInfoApi movieInfoApi = new KobisSearchMovieInfoApi();
	
		String movieCode = movieListApi.searchMovieCode(contentTitle);
		
		Contents content = new Contents();
		content.setMovieNm(contentTitle);
		content.setMovieCd(movieCode);
		contentService.insertContent(content);
		
		JSONObject contentObject = movieInfoApi.searchMovieInfo(movieCode);
		//System.out.println(contentObject);
		
		ModelAndView mv = new ModelAndView(); 
		mv.addObject("content", contentObject.toString());
		mv.setViewName("contents/getContent.html");
		return mv;
		
	}
	
	
    

	
}

package com.spring.boardweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boardweb.controller.api.KobisSearchDailyBoxOfficeApi;
import com.spring.boardweb.controller.api.NaverMovieApi;

@RestController
public class HomeController {
	
	//깃 연습 - cyh
	//설정 실수해서 레포지토리 다시 만들고 재시도 - cyh
	
	@GetMapping("/")
	public ModelAndView mainPage() throws ParseException {
		ModelAndView mv = new ModelAndView();
		
		KobisSearchDailyBoxOfficeApi dailyBoxOfficeApi= new KobisSearchDailyBoxOfficeApi();
		NaverMovieApi naverMovieApi = new NaverMovieApi();
		
		//////////////
		ArrayList<String> dailyKorBoxOfficeTitleList = dailyBoxOfficeApi.dailyKorBoxOfficeTitle();
		for (int i=0; i<dailyKorBoxOfficeTitleList.size(); i++) {
			String title = dailyKorBoxOfficeTitleList.get(i);
			mv.addObject("korRankNumber" + i , naverMovieApi.searchMovie(title));
		}
		
		ArrayList<String> dailyForeignBoxOfficeTitleList = dailyBoxOfficeApi.dailyForeignBoxOfficeTitle();
		for (int i=0; i<dailyForeignBoxOfficeTitleList.size(); i++) {
			String title = dailyForeignBoxOfficeTitleList.get(i);
			mv.addObject("foreignRankNumber" + i , naverMovieApi.searchMovie(title));
		}
		//////////////박스오피스 순위대로 영화 제목 가져오기(kobis api), 포스터 이미지 가져오기(네이버 api)
		
		
		HashMap<String, String> dailyKorBoxOfficeMap = dailyBoxOfficeApi.dailyKorBoxOffice();
		//영화제목과 영화코드를 담은 맵을 가져옴
		Iterator<String> itr= dailyKorBoxOfficeMap.keySet().iterator();
		while (itr.hasNext()) {
			String movieTitle = itr.next();
			String movieCode = dailyKorBoxOfficeMap.get(movieTitle);
			mv.addObject(movieTitle, movieCode); //mv.addObject("범죄도시2", "20204548")
		}
		
		mv.setViewName("index.html");
		
		return mv;
	}
}

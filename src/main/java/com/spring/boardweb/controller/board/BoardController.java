package com.spring.boardweb.controller.board;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boardweb.entity.Faq;
import com.spring.boardweb.entity.Notice;
import com.spring.boardweb.service.board.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	//효주수정222333
	@GetMapping("/getNotice")
	public ModelAndView getNoticeView(Notice notice,
									  @PageableDefault(page = 0, size = 10) Pageable pageable) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/getNotice.html");
		
		//검색조건이 null이 아니거나 ""이 아닐때, 화면단으로 보내준다.
		if(notice.getSearchCondition() != null && !notice.getSearchCondition().equals("")) {
			mv.addObject("searchCondition", notice.getSearchCondition());
		}
		//검색키워드가 null이 아니거나 ""이 아닐때, 화면단으로 보내준다.
		if(notice.getSearchKeyword() != null && !notice.getSearchKeyword().equals("")) {
			mv.addObject("searchKeyword", notice.getSearchKeyword());
		}
		
		Page<Notice> noticeList = boardService.getNotice(notice, pageable);
		mv.addObject("noticeList", noticeList);
		
		return mv;
	}
	
	@GetMapping("/getFaq")
	public ModelAndView getFaqView(Faq faq,
			  					  @PageableDefault(page = 0, size = 10) Pageable pageable) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/getFaq.html");
		
		//검색조건이 null이 아니거나 ""이 아닐때, 화면단으로 보내준다.
		if(faq.getSearchCondition() != null && !faq.getSearchCondition().equals("")) {
		mv.addObject("searchCondition", faq.getSearchCondition());
		}
		//검색키워드가 null이 아니거나 ""이 아닐때, 화면단으로 보내준다.
		if(faq.getSearchKeyword() != null && !faq.getSearchKeyword().equals("")) {
		mv.addObject("searchKeyword", faq.getSearchKeyword());
		}
		
		Page<Faq> faqList = boardService.getFaq(faq, pageable);
		mv.addObject("faqList", faqList);
		
		return mv;
	}
	
	@GetMapping("/getAsk")
	public ModelAndView getAskView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/getAsk.html");
		return mv;
	}
	
	@GetMapping("/insertNotice")
	public ModelAndView insertNoticeView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/insertNotice.html");
		return mv;
	}
	
	@GetMapping("/insertFaq")
	public ModelAndView insertFaqView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/insertFaq.html");
		return mv;
	}
	
	@PostMapping("/insertNotice")
	public void insertNotice(HttpServletResponse response, 
							 Notice notice) throws IOException {	
		int noticeSeq = boardService.insertNotice(notice);
		response.sendRedirect("/board/getNotice");
	}
	
	@PostMapping("/insertFaq")
	public void insertFaq(HttpServletResponse response,
						  Faq faq) throws IOException {
		int faqSeq = boardService.insertFaq(faq);
		response.sendRedirect("/board/getFaq");
	}
	
	@GetMapping("/deleteNotice/{noticeSeq}")
	public void deleteNotice(@PathVariable int noticeSeq, HttpServletResponse response) throws IOException{
		boardService.deleteNotice(noticeSeq);
		response.sendRedirect("/board/getNotice");
	}
	
	@GetMapping("/deleteFaq/{faqSeq}")
	public void deleteFaq(@PathVariable int faqSeq, HttpServletResponse response) throws IOException{
		boardService.deleteFaq(faqSeq);
		response.sendRedirect("/board/getFaq");
	}
	
	//////////////////////////////////////////////////
	@GetMapping("/updateNotice/{noticeSeq}")
	public ModelAndView updateNoticeView(@PathVariable int noticeSeq) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("notice", boardService.getNotice(noticeSeq));
		mv.setViewName("board/updateNotice.html");
		return mv;
	}
	
	@GetMapping("/updateFaq/{faqSeq}")
	public ModelAndView updateFaqView(@PathVariable int faqSeq) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("faq", boardService.getFaq(faqSeq));
		mv.setViewName("board/updateFaq.html");
		return mv;
	}
	
	@PostMapping("/updateNotice")
	public void updateNotice(Notice notice, HttpServletResponse response) throws IOException{
		boardService.updateNotice(notice);
		response.sendRedirect("/board/getNotice");
	}
	
	@PostMapping("/updateFaq")
	public void updateFaq(Faq faq, HttpServletResponse response) throws IOException{
		boardService.updateFaq(faq);
		response.sendRedirect("/board/getFaq");
	}
	
}

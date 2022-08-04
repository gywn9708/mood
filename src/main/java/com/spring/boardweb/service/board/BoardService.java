package com.spring.boardweb.service.board;

import java.util.List;


import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boardweb.entity.Faq;
import com.spring.boardweb.entity.Notice;

public interface BoardService {

	
	Page<Notice> getNotice(Notice notice, Pageable pageable);
	
	Page<Faq> getFaq(Faq faq, Pageable pageable);

	int insertNotice(Notice notice);

	int insertFaq(Faq faq);

	void deleteNotice(int noticeSeq);
	
	void deleteFaq(int faqSeq); 

	void updateNotice(Notice notice);

	void updateFaq(Faq faq);
	
	Notice getNotice(int noticeSeq);

	Faq getFaq(int faqSeq);











}

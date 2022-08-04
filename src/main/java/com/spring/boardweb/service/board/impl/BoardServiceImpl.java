package com.spring.boardweb.service.board.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boardweb.entity.Faq;
import com.spring.boardweb.entity.Notice;
import com.spring.boardweb.mapper.BoardMapper;
import com.spring.boardweb.repository.FaqRepository;
import com.spring.boardweb.repository.NoticeRepository;
import com.spring.boardweb.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	NoticeRepository noticeRepository;
	
	@Autowired
	FaqRepository faqRepository;
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public Page<Notice> getNotice(Notice notice, Pageable pageable) {
		if(notice.getSearchKeyword() != null && !notice.getSearchKeyword().equals("")) { //검색어가 있을 경우
			if(notice.getSearchCondition().equals("all")) {
				return noticeRepository.findByNoticeTitleContainingOrNoticeContentContainingOrNoticeKindContaining(
							notice.getSearchKeyword(),
							notice.getSearchKeyword(),
							notice.getSearchKeyword(),
							pageable
						);
			} else if(notice.getSearchCondition().equals("title")) {
				return noticeRepository.findByNoticeTitleContaining(
						notice.getSearchKeyword(), 
							pageable);
				
			} else if(notice.getSearchCondition().equals("content")) {
				return noticeRepository.findByNoticeContentContaining(
						notice.getSearchKeyword(), 
							pageable);
				
			} else if(notice.getSearchCondition().equals("kind")) {
				return noticeRepository.findByNoticeKindContaining(
						notice.getSearchKeyword(), 
							pageable);
			} else {
				return null;
			}
		} else { //검색어가 없을 경우
			return noticeRepository.findAll(pageable);
		}
	}
	
	
	@Override
	public Page<Faq> getFaq(Faq faq, Pageable pageable) {
		if(faq.getSearchKeyword() != null && !faq.getSearchKeyword().equals("")) { //검색어가 있을 경우
			if(faq.getSearchCondition().equals("all")) {
				return faqRepository.findByFaqTitleContainingOrFaqContentContainingOrFaqKindContaining(
							faq.getSearchKeyword(),
							faq.getSearchKeyword(),
							faq.getSearchKeyword(),
							pageable
						);
			} else if(faq.getSearchCondition().equals("title")) {
				return faqRepository.findByFaqTitleContaining(
						faq.getSearchKeyword(), 
							pageable);
				
			} else if(faq.getSearchCondition().equals("content")) {
				return faqRepository.findByFaqContentContaining(
						faq.getSearchKeyword(), 
							pageable);
				
			} else if(faq.getSearchCondition().equals("kind")) {
				return faqRepository.findByFaqKindContaining(
						faq.getSearchKeyword(), 
							pageable);
			} else {
				return null;
			}
		} else { //검색어가 없을 경우
			return faqRepository.findAll(pageable);
		}
	}
	
	@Override
	public int insertNotice(Notice notice) {
		noticeRepository.save(notice);
		return notice.getNoticeSeq();
	}
	
	@Override
	public int insertFaq(Faq faq) {
		faqRepository.save(faq);
		return faq.getFaqSeq();
	}

	@Override
	public void deleteNotice(int noticeSeq) {
		noticeRepository.deleteById(noticeSeq);
	}
	
	@Override
	public void deleteFaq(int faqSeq) {
		faqRepository.deleteById(faqSeq);
	}
	
	@Override
	public void updateNotice(Notice notice) {
		noticeRepository.save(notice);
	}

	@Override
	public void updateFaq(Faq faq) {
		faqRepository.save(faq);	
	}

	@Override
	public Notice getNotice(int noticeSeq) {
		return noticeRepository.findById(noticeSeq).get();
	}
	
	@Override
	public Faq getFaq(int faqSeq) {
		return faqRepository.findById(faqSeq).get();
	}


	




}

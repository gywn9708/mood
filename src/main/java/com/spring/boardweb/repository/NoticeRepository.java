package com.spring.boardweb.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boardweb.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
		
	
	Page<Notice> findByNoticeTitleContainingOrNoticeContentContainingOrNoticeKindContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3, Pageable pageable);

	Page<Notice> findByNoticeTitleContaining(String searchKeyword, Pageable pageable);
	
	Page<Notice> findByNoticeContentContaining(String searchKeyword, Pageable pageable);
	
	Page<Notice> findByNoticeKindContaining(String searchKeyword, Pageable pageable);
		
}

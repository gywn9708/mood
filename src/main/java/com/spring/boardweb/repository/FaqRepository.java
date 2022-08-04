package com.spring.boardweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boardweb.entity.Faq;

public interface FaqRepository extends JpaRepository<Faq, Integer> {


	Page<Faq> findByFaqTitleContainingOrFaqContentContainingOrFaqKindContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3, Pageable pageable);

	Page<Faq> findByFaqTitleContaining(String searchKeyword, Pageable pageable);
	
	Page<Faq> findByFaqContentContaining(String searchKeyword, Pageable pageable);
	
	Page<Faq> findByFaqKindContaining(String searchKeyword, Pageable pageable);
	
	
	
}

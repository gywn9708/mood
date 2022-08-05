package com.spring.boardweb.service.contents.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boardweb.entity.Contents;
import com.spring.boardweb.repository.ContentRepository;
import com.spring.boardweb.service.contents.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentRepository contentRepository;
	
	@Override
	public void insertContent(Contents content) {
		contentRepository.save(content);
	}

}

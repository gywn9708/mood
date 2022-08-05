package com.spring.boardweb.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="T_MOOD_CONTENTS")
@Data
public class Contents { //영화 컨텐츠를 조회할 때마다 인서트되도록 함
	
	@Id
	private String movieCd; //영화 코드
	
	private String movieNm; //영화 제목

}

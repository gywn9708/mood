package com.spring.boardweb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="M_CONTENT")
@Data
@SequenceGenerator(
		name="M_CONTENT_ID_GENERATOR", //name: SequenceGenerator의 이름을 지정
		sequenceName="CONTENT_ID", //sequenceName: DB에 생성될 시퀀스의 이름 지정 
		initialValue=1, //initialValue: 초기 값 설정 
		allocationSize=1 //allocationSize: 몇 씩 증가할지 설정 
		)
public class Content {
	
	@Id //키 값 생성 전략을 설정함
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "M_CONTENT_ID_GENERATOR") 
	private int ContentId; //작품 id

	private int CategoryNum; //작품 코드. 카테고리 테이블의 pk와 연결해야 함	
	
	private String ContentTitle; //제목
	private String ContentRelease; //개봉날짜
	private String ContentGrade; //등급
	private String ContentGenre; //장르
	private String ContentCountry; //영화
	private String ContentTime; //상영시간
	private String ContentMaker; //제작자
	private String ContentDirector; //감독
	private String ContentLead; //주연배우
	private String ContentSub; //조연배우
	private String ContentShort; //단역배우 
	private String ContentStory; //줄거리
	//private String ContentReco; //추천작품
	



}

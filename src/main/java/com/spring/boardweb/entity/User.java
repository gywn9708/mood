package com.spring.boardweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity //이 객체가 엔티티 객체임을 선언함, 필수 값!
@Table(name="M_USER")//필수 값은 아님. 객체명과 테이블명이 다를 때 테이블명을 지정하기 위해 사용한다. 
@Data 
public class User {
	
	@Id //키 값으로 지정. 객체 안에 있는 모든 속성 값들이 컬럼으로 생성된다.
	private String userId; 
	
	@Column(nullable=false)
	private String userPw;
	
	@Column(nullable=false)
	private String userNm;
	
	@Column(nullable=false)
	private String userNickNm;
	
	@Column(nullable=false)
	private String userMail;
	
	@Column(nullable=false)
	private String userBirth;
	
	@Column(nullable=false)
	private String userGender;
	
	@Column(nullable=false)
	private String userTel;
	
	//@Transient 해당 속성 값은 컬럼으로 생성하지 않음
	@Transient 
	private String userBirthYear;
	
	@Transient
	private String userBirthMonth;
	
	@Transient
	private String userBirthDay;

}

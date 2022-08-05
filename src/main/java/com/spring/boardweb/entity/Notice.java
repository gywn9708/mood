package com.spring.boardweb.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="T_MOOD_NOTICE")
@Data
@SequenceGenerator(
		name="M_NOTICE_SEQ_GENERATOR", //name: SequenceGenerator의 이름을 지정
		sequenceName="NOTICE_SEQ", //sequenceName: DB에 생성될 시퀀스의 이름 지정 
		initialValue=1, //initialValue: 초기 값 설정 
		allocationSize=1 //allocationSize: 몇 씩 증가할지 설정 
		)
public class Notice {
	
	@Id //키 값 생성 전략을 설정함
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "M_NOTICE_SEQ_GENERATOR") 
	private int noticeSeq;
	private String noticeTitle;
	private String noticeKind;
	private String noticeContent;
	private LocalDateTime noticeRegdate = LocalDateTime.now();

	//컬럼으로는 생성되지 않고, 엔티티가 값은 가지고 있을 수 있도록 설정
	@Transient
	private String searchCondition;
	
	@Transient
	private String searchKeyword;
	
}

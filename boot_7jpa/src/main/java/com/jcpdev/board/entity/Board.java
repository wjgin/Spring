package com.jcpdev.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="board")	// boar 테이블과 매핑(클래스명과 동일하다면 생략가능)
@Entity	// 이 클래스는 테이블과 바로 매핑되는 엔티티
public class Board extends BaseEntity{
	// 테이블의 column과 매핑되도록 선언
	@Id	// 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bidx;			// int는 10자리 정수
	private String subject;
	private String content;
	
	
	@ManyToOne				// 다대일 : 한 사용자가 여러개의 글을 작성 가능
	private Users writer; 	// 단방향 관계 설정(Users에는 관계 설정 안함)
}

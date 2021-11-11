package com.jcpdev.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "customer")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idx;
	@Column(updatable = false)
	private String name;
	
	@Column(updatable = false)
	private String password;
	
	private String email;
	private String addr;

	@Column
	private String gender;
	
	private Integer age;	// entity에서는 기본형대신 래퍼 클래스로 함(값이 없으면 null로 처리 => nullPointException 처리 방지)
	private String hobby;
}

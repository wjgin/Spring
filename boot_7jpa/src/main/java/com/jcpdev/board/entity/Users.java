package com.jcpdev.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="users")
@Entity
public class Users extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mno;
	
	@Column(nullable = false, unique = true)	// 컬럼 어노테이션, 변수명과 같으면 생략가능
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String name;
}

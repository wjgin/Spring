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
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name="users")
@Entity
public class Users extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mno;
	
	@Column(nullable = false, unique = true)	// 컬럼 어노테이션, 변수명과 같으면 생략가능
	private String email;
	
	@Column(updatable = false)	// update할 경우 password 컬럼은 미 포함
	private String password;
	
	@Column
	private String name;
}

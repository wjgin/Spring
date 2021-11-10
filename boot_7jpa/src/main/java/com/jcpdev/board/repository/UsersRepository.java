package com.jcpdev.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcpdev.board.entity.Users;

// JpaRepository<Users, Long> : <Entity type, Entity의 기본키>
public interface UsersRepository extends JpaRepository<Users, Long>{
	// 커스텀 메소드 정의할 수 있음.
	
	
}

package com.jcpdev.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jcpdev.board.entity.Users;
import com.jcpdev.board.repository.UsersRepository;

@SpringBootTest
class Boot7jpaApplicationTests {
	@Autowired
	UsersRepository udao;
	
	@Test
	void contextLoads() {
		Users user = Users.builder()
				.email("momo@gmail.com")
				.password("1111")
				.name("모모")
				.build();
		
		// save 메소드는 insert, update sql
		udao.save(user);
		
	}

}

package com.jcpdev.board;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jcpdev.board.entity.Board;
import com.jcpdev.board.entity.Users;
import com.jcpdev.board.repository.BoardRepository;
import com.jcpdev.board.repository.UsersRepository;

@SpringBootTest
class Boot7jpaApplicationTests {
	@Autowired
	UsersRepository udao;
	
	@Autowired
	BoardRepository bdao;
	
	// jpa + 3rd Party 라이브러리
	@Test
	public void getBoard() {
		// 자동 join (board left join users => writer_mno 관계가 설정되어있기 때문)
		System.out.println(bdao.findById(3L));
	}
	
	public void insertBoard() {
	      for(int i=1;i<20;i++) {
	         Random random = new Random();
	         // random.nextInt(9) : 0 <= 랜덤 < 9, Long.valueOf: int를 Long타입으로 변환
	         Users user = Users.builder().mno(Long.valueOf(random.nextInt(4)+3)).build();
	         Board board=Board.builder().subject("제목"+i).content("내용" +i+"테스트" + (i*11)).writer(user).build();
	         bdao.save(board);
	      }
	   }
	
	// Users delete
	public void delete() {
		udao.deleteById(2L);	// 기본키 조건의 delete 쿼리
	}
	
	public void getOne() {
		// Optional 타입은 Users 타입의 래퍼
		// users 객체가 null 일때, 메소드 실행 nullpointexception 발생 x => Optional은 null도 담음
		Optional<Users> users = udao.findById(8L);
		Users user;
//		if(users.isPresent())
//			user = users.get();
//		else
//			user = null;
		user = users.orElse(new Users());	// orElse: null이면 인자를 반환함
		System.out.println(user);
		System.out.println(users);
	}
	
	public void update() {
		// 2L -> 기본키가 2인 Long type
        Users member=Users.builder().mno(2L).email("serii@jcp.kr").name("세리park" ).build();
        udao.save(member);
  }
	
	void insertUsers() {
		// 자바 람다식
		IntStream.range(1, 6).forEach(i -> {
			Users member = Users.builder()
					.email("momo" + i + "@naver.com")
					.password("2222" + (i*12))
					.name("김모모" + i)
					.build();
			udao.save(member);
		});
	}
	
	void insertUser() {
		Users user = Users.builder()
				.email("momo@gmail.com")
				.password("1111")
				.name("모모")
				.build();
		
		// save 메소드는 insert, update sql
		udao.save(user);
		
	}

}

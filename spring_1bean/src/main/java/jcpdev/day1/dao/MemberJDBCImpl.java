package jcpdev.day1.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jcpdev.dto.Member;

@Component("memberJdbc") // bean의 이름 변경가능
public class MemberJDBCImpl implements MemberDao {
	
	
//	public MemberDaoImpl() {
//		System.out.println("MemberDaoImpl 기본 생성자 create.////");
//		
//	}

	// @Value를 이용해서 값을 주입하면 기본생성자 필요 x
	public MemberJDBCImpl(@Value("제이디비씨")String message) { 
		//bean이 생성되는 순서를 확인하기 위해 작성한 출력문
		System.out.println("MemberJDBC 생성!");
		System.out.println("message= "+message);
	}
	
	public Member findMember(int idx) {
		System.out.println("MemberJDBC findMember()///////////////////");
		return new Member(idx,"JDBC@naver.com", "5959");
	}
	
}

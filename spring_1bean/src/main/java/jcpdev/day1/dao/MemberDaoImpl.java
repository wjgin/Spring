package jcpdev.day1.dao;

import org.springframework.stereotype.Component;

import jcpdev.dto.Member;

@Component
public class MemberDaoImpl implements MemberDao {
	
	public MemberDaoImpl() {}
	
	public MemberDaoImpl(String message) {
		//bean이 생성되는 순서를 확인하기 위해 작성한 출력문
		System.out.println("MemberDaoIpl 생성!");
		System.out.println(message);
	}
	
	public Member findMember(int idx) {
		System.out.println("MemberDaoImpl findMember()///////////////////");
		return new Member(idx,"honey@naver.com", "1212");
	}
	
}

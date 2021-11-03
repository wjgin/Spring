package jcpdev.day1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jcpdev.day1.dao.MemberDao;
import jcpdev.dto.Member;

@Component
public class MemberServiceMyImpl implements MemberService {
	// 수정하지 못하게하고 생성자 주입에서 누구인지 특정 값을 정해준다.
	private final MemberDao dao;
	
	// public MemberServiceMyImpl() {}

	@Autowired	
	public MemberServiceMyImpl(@Qualifier(value="memberDao")MemberDao dao) {
		System.out.println("MemberService마이Impl create.///////////////////");
		this.dao = dao;
	}

	public Member findMember(int idx) {
		
		System.out.println("MemberServiceMyImpl findMember()///////////////////");
		return dao.findMember(idx);
	}

}

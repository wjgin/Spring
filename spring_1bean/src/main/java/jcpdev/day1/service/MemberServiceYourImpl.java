package jcpdev.day1.service;

import jcpdev.day1.dao.MemberDao;
import jcpdev.dto.Member;

public class MemberServiceYourImpl implements MemberService{
	private MemberDao dao;

	public MemberServiceYourImpl(MemberDao dao) {
		System.out.println("MemberServiceYourImpl create ///////////////////");
		this.dao = dao;
	}    
		
		public Member findMember(int idx) {
		System.out.println("MemberServiceYourImpl findMember() ///////////////////");
			return dao.findMember(idx*10);
		}	
}

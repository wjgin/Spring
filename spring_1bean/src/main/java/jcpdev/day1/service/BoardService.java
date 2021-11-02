package jcpdev.day1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jcpdev.day1.dao.BoardDao;

@Component
public class BoardService {   
	//비지니스 로직 처리.(데이터 연산,가공)
	//여러 dao 클래스를 사용할 수 있다.
	
	@Autowired
	private BoardDao dao;   //의존관계
	
	
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}
	
	public void dataRead() {
		System.out.println("service dataRead()------");
		dao.dataRead();
		
	}

}

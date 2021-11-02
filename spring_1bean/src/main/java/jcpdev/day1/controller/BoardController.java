package jcpdev.day1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jcpdev.day1.service.BoardService;


@Component	// bean으로 생성
public class BoardController {  //웹애플리케이션에서 클라이언트 요청 처리
	@Autowired	// BoardService 타입의 빈을 주입한다.
	private BoardService service; 	// 의존관계

	// 의존관계 주입방법1: setter
	public void setService(BoardService service) {
		this.service = service;
	}
	
	// 의존관계 주입방법2: 커스텀 생성자
	public void dataRead() {
		System.out.println("controller dataRead()------");
		service.dataRead();
	}

}

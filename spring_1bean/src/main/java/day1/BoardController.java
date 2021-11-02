package day1;


public class BoardController {  //웹애플리케이션에서 클라이언트 요청 처리
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

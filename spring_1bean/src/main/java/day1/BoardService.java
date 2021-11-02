package day1;


public class BoardService {   
	//비지니스 로직 처리.(데이터 연산,가공)
	//여러 dao 클래스를 사용할 수 있다.
	
	private BoardDao dao;   //의존관계
	
	
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}
	
	public void dataRead() {
		System.out.println("service dataRead()------");
		dao.dataRead();
		
	}

}

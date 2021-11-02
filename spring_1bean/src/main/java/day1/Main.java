package day1;

// 기존의 자바 의존관계(강한 결합)
public class Main {
	
	public static void main(String[] args) {
		BoardController controller = new BoardController();
		BoardService service = new BoardService();
		
		service.setDao(new BoardDao());
		
		controller.setService(service);
		controller.dataRead();
	}
}

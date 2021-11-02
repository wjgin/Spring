package day1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp1 {

	public static void main(String[] args) {
		// bean을 관리하는 스피링 컨테이너에 접근하는 객체를 생성 합니다. -context
		// 이때, bean설정파일을 지정 합니다. 스프링컨테이너는 bean을 생성하고 저장소에 등록하여 관리합니다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext.xml");
	
		// getBean() 메소드의 return은 Object타입 => 캐스팅 필요
		BoardController controller = (BoardController) context.getBean("boardController");
		controller.dataRead();

	
		Singer singer = (Singer) context.getBean("singer");
		System.out.println(singer);
		
		// bean 생성의 기본값은 싱글턴
		BoardController controller2 = (BoardController) context.getBean("boardController");
		System.out.println("\ncontroller와 controller2는 같은 메모리상에 존재하는 같은 객체일까?");
		System.out.println(controller == controller2);	// true
		
		// scope을 prototype으로 설정하면 bean을 가져올 때마다 새로운 객체를 생성한다.
		Singer singer2 = (Singer) context.getBean("singer");
		System.out.println("\nsinger와 singer2는 같은 객체일까?");
		System.out.println(singer == singer2);	// false
		singer2.setSinger("원더걸스");
		System.out.println(singer);
		System.out.println(singer2);
	}

}

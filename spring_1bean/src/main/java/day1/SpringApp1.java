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
	}

}

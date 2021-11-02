package day1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// context를 생성하면 bean들은 생성된다(생성자가 실행).
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext2.xml");
		
		MemberController controller =  (MemberController) context.getBean("memberController");
		System.out.println(controller.findMember(23));
	}

}

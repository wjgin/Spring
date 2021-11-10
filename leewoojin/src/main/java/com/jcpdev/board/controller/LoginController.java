package com.jcpdev.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jcpdev.board.model.Customer;
import com.jcpdev.board.service.CustomerService;

@Controller
@SessionAttributes("customer")
public class LoginController {
	
	private final CustomerService service;
	
	public LoginController(CustomerService service) {
		this.service = service;
	}
	
	
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(String alert,Model model) {
		if(alert != null && alert.equals("y")) {
			model.addAttribute("message","로그인이 필요합니다.");
			model.addAttribute("url","login");
			return "alertLogin";
		}
		return "login"; //로그인 버튼 -> login.jsp(뷰) - > 로그인정보 입력후 버튼 ->
	}
	
	//위에서 입력한 로그인 정보를 Model 객체로 전달받는다.
	@RequestMapping(value = "login",method = RequestMethod.POST)
						//@ModelAttribute("customer") Customer customer에서 생략된 상태
	public String loginOk(String email,String password , Model model) { // customer가 모델객체임.(사용자 입력 로그인정보 저장된상태) 
		//System.out.println(customer); model 객체가 적용되어서 이름 안나옴
		Customer result = service.login(Customer.builder().email(email).password(password).build());
		//client측에서 password 안나오게 하려고 빌더패턴으로 구현 
		if(result != null) { //로그인 성공
			model.addAttribute("customer", result);
			return "home"; //정상 로그인 후 -> home.jsp
		}else { //로그인 실패
			model.addAttribute("message","로그인 정보가 틀립니다.");
			model.addAttribute("url","login");
			return "alertLogin";
		}
	}
	
	@RequestMapping(value = "logout")
	public String logout(SessionStatus status) {// @SessionAttributes로 설정된것은 SessionStatus로 지운다.
		status.setComplete(); //@SessionAttributes 로 설정된 애트리뷰트를 clear 한다.
		return "redirect:/";
	}
	//status.setComplete(); - JSESSIONID 는 변하지 않고 @SessionAttributes로 설정된 애트리뷰트 값을 clear한다.
	//						- HttpSession의 removeAttribute() 메소드 동작과 유사
	//session.invalidate(); - 서버가 JSESSIONID는 새로 부여해주지만 @SessionAttributes로 설정된 값은 남아있다
	
	
	
	
}

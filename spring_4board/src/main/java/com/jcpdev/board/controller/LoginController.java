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
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(String alert, Model model) {
		
		if(alert != null && alert.equals("y")) {
	        model.addAttribute("message","로그인이 필요합니다." );  
	        model.addAttribute("url","login");
	        return "alertLogin";
		} else {
			return "login"; // 로그인 버튼 -> login.jsp(view) -> 로그인 입력 후 버튼 -> post
		}
	}
	
	// 로그인 정보를 Model객체로 전달
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginOk(String email, String password, Model model) {	// 보안을 위해서 비밀번호가 model에 안남아있게하기 위해서 파라미터로 받기
		Customer result= service.login(Customer.builder().email(email).password(password).build());
		if(result != null) {
			// 로그인 성공 - session에 result를 저장
			// customer.setPassword(null);	// 서버에서 모델 비밀번호 지워버리기(모델의 경우 남아있기에)
			model.addAttribute("customer", result);	// @SessionAttributes로 sessionScope에 저장함
			return "home";
		} else {
			// 로그인 실패
			String message="로그인 정보가 틀립니다.";
	        model.addAttribute("message",message );  
	        model.addAttribute("url","login");
	        return "alertLogin";
		}
	}
	
	@RequestMapping(value="logout")
//	public String logout(HttpSession session) {
	public String logout(SessionStatus status) {
//		session.invalidate();  // 서버가 JSESSIONID는 새로부여, @SessionAttributes 설정은 남아있다.
		status.setComplete();  // @SessionAttributes로 설정된 애트리뷰트를 clear 한다.
								// ㄴ HttpSession의 removeAttribute() 메소드와 유사
		return "redirect:/";
	}
	
	
	
}

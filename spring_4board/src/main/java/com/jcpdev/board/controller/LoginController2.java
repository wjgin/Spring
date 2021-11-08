package com.jcpdev.board.controller;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.board.model.Customer;
import com.jcpdev.board.service.CustomerService;

public class LoginController2 {
	
	private final CustomerService service;
	
	public LoginController2(CustomerService service) {
		this.service = service;
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		
		return "login"; // 로그인 버튼 -> login.jsp(view) -> 로그인 입력 후 버튼 -> post
	}
	
	// 로그인 정보를 Model객체로 전달
	@RequestMapping(value = "login", method = RequestMethod.POST)
	//  @ModelAttribute("customer")가 생략됨. Model객체는 계속 남아있음(request로, 파라미터처럼 1회성이 아님)
	//		ㄴ 파라미터가 자동 매핑되어서 객체가 생성됨
	//	public String loginOk(Customer customer, Model model, HttpSession session) {	// Customer은 model객체(로그인 정보가 저장된 상태)
	public String loginOk(String email, String password, Model model, HttpSession session) {	// 보안을 위해서 비밀번호가 model에 안남아있게하기 위해서 파라미터로 받기
		Customer result= service.login(Customer.builder().email(email).password(password).build());
		if(result != null) {
			// 로그인 성공 - session에 result를 저장
			// customer.setPassword(null);	// 서버에서 모델 비밀번호 지워버리기(모델의 경우 남아있기에)
			session.setAttribute("customer", result);
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
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
	
	
	
}

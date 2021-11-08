package com.jcpdev.board.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.board.model.Customer;
import com.jcpdev.board.service.CustomerService;

@Controller
public class LoginController {
	
	private final CustomerService service;
	
	public LoginController(CustomerService service) {
		this.service = service;
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		
		return "login"; // 로그인 버튼 -> login.jsp(view) -> 로그인 입력 후 버튼 -> post
	}
	
	// 로그인 정보를 Model객체로 전달
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginOk(Customer customer, Model model, HttpSession session) {	// Customer은 model객체(로그인 정보가 저장된 상태)
		// 로그인 정보 일치 확인
		Customer result = service.login(customer);
		if(result != null) {
			// 로그인 성공 - session에 result를 저장
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

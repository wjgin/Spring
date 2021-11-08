package com.jcpdev.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.jcpdev.board.model.Customer;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		
		if(customer == null) {
			response.sendRedirect(request.getContextPath() +"/login?alert=y");
			return false;	// handler 메소드로 이동하지 않음
		} else {
			// 로그인이 된 상태이므로 요청에 따라 handler 메소드로 이동
			return true;
		}
		
	}
}

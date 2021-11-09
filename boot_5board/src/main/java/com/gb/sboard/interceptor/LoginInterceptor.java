package com.gb.sboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gb.sboard.dto.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("=======================preHandler=========================");
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		
		if(customer == null) {
			log.debug("=======================로그인 필요=========================");
			response.sendRedirect(request.getContextPath() +"/");
			return false;	// handler 메소드로 이동하지 않음
		} else {
			// 로그인이 된 상태이므로 요청에 따라 handler 메소드로 이동
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("=======================postHandler=========================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("=======================afterCompeletion=========================");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}

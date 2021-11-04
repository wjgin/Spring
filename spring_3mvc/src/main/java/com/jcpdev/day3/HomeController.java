package com.jcpdev.day3;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.day3.model.Order;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {	// Locate locale: 다국어처리
		logger.info("Welcome home! The client locale is {}.", locale);	// log 출력 기능
		
		Date date = new Date();	// 현재 날짜 시간
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );	// request.setAttribute("serverTime", formattedDate)
		
		return "home";	// view: home.jsp
	}
	
	@RequestMapping(value="test") // - 요청의 value가 view 파일명
	public void test() {
		
	}
	
	@RequestMapping(value="hello")
	public void test2() {
		// return으로 view 정보가 없을 때 기본 동작은? 
	}
	
	@RequestMapping(value="form")
	public void form() {
		
	}
	
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public void regist(Order order) {	// form.jsp(View)의 입력 -> action="regist" -> 입력값이 order에 전달
										// @ModelAttribute가 생략(attribute이름은 자동으로 order) -> regist.jsp에 출력
										// attribute이름 변경 ex: @ModelAttribute(name="od")
		System.out.println(order);
		
	}									
	
	
	
}

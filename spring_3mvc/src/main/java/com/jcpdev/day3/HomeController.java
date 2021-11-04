package com.jcpdev.day3;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="regist", method=RequestMethod.GET) // regist가 GET으로 들어올 때,
	public String form() {
	return "form";	// form.jsp view로 요청 전달
	}
	
	
	@RequestMapping(value="regist", method=RequestMethod.POST)	// jsp와 명확한 확인을 위해서 method 속성을 주거나 GET 방식을 추가해서 사용 가능
	public void regist(Order order) {	// form.jsp(View)의 입력 -> action="regist" -> 입력값이 order에 전달
										// @ModelAttribute가 생략(attribute이름은 자동으로 order) -> regist.jsp에 출력
										// attribute이름 변경 ex: @ModelAttribute(name="od")
		System.out.println(order);
		
	}					
	
	@RequestMapping(value="list")
	public void list(String page) {	// 인자가 파라미터: @RequestParam 생략, required = false가 기본값
		
		// if(page == null || page.equals("")) page = "1";
		System.out.println("page 파라미터 값 = " + page);
		
		// int page로 선언시
		// 파라미터가 없다면 500 내부서버 오류
		// 파라미터는 있지만 값이 없다면 400 잘못된 요청(파라미터는 있지만 null을 int로 변환이 불가능)
	}
	
	@RequestMapping(value="getone")
	public void getOne(@RequestParam(required = true) String idx, Model model) { // 파라미터가 없으면 오류(파라미터는 필수)
		System.out.println("idx 파라미터 값 = " + idx);
		// 파라미터가 없다면 400오류
		
		// attribute를 넘기기 위해선 model이 필요 => 일반형을 넘기기 위해 모델 형태로 넘기기
		model.addAttribute("idx", idx);
	}
	
	// 파라미터 두개
	@RequestMapping(value="param2")
	public String param2(String idx, String search) {
		
		System.out.println("idx = " + idx);
		System.out.println("search = " + search);
		return "home";
	}
	
	@RequestMapping(value = "paramn")
	public String paramn(@RequestParam Map<String, String> param, Model model) {	
		// map으로 여러 파라미터를 받을 수 있음
		// @RequestParam 생략할 수 없음
		System.out.println(param.get("idx"));
		System.out.println(param.get("name"));
		System.out.println(param.get("search"));
		System.out.println(param.get("cnt"));
		System.out.println(param.get("page"));
		
		model.addAllAttributes(param);	// Map을 넘겨주는 모델 메소드 사용
		
		return "paramn";
	}
	
	// 파라미터 값이 일치할 때 요청과 매핑
	@RequestMapping(value="param", params = "action=delete") // params = "action" 의 경우 action 파라미터가 들어오면 모두 실행
	public String param() {
		System.out.println("action=delete 입니다.");
		return "home";
	}
	
	
	// 리턴 값으로 Model과 View를 전달하는 ModelAndView 객체 사용
	@RequestMapping(value="mv")
	public ModelAndView my() {
		// 모델이 담아서 넘길 order 객체 생성 후 데이터 넣기
		Order order = new Order();
		order.setAmount(100);
		order.setId("admin");
		order.setDevDate(java.sql.Date.valueOf(LocalDate.of(2011, 11, 11)));
		
		// 데이터 객체를 담을 모델맵을 생성 후 order객체 담기
		ModelMap model = new ModelMap();
		model.addAttribute("order", order);
		
		// view파일과 데이터가 담긴 model을 리턴하기
		return new ModelAndView("regist", model);
	}
		
}

package com.jcpdev.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("serverTime")	// serverTime 이름의 애트리뷰트는 sessionScope이다. => 애트리뷰트를 sessionScope에 저장
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * @Autowired GalleryImpl service;
	 */
	
	// request.getContextPath : http://localhost:portNum/projectname/ 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/*
	 * @RequestMapping(value = "/gallery", method = RequestMethod.GET) public String
	 * gallery(Model model) { logger.info("Welcome gallery!");
	 * 
	 * 
	 * model.addAttribute("list", service.getAll());
	 * 
	 * return "gallery/gallery"; }
	 */
	
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	
	@GetMapping({"/", "/test"})	// @RequestMapping에서 RequestMthod.GET 
	public String test() {
		return "test";
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "test";
	}
}

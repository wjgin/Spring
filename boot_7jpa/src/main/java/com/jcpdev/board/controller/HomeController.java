package com.jcpdev.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/customers")
	public void custmers() {
		// view는 customers.html
	}
}

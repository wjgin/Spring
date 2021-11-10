package com.jcpdev.board;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jcpdev.board.interceptor.LoginInterceptor;

// @Configuration
public class ApplicationConfig  implements WebMvcConfigurer{
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns= Arrays.asList("/community/*","/community/*");
		List<String> excludes=Arrays.asList("/community/list","/community/detail","/customer/join");
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns(patterns).excludePathPatterns(excludes);
	}
	
}

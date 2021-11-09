package com.gb.sboard.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gb.sboard.interceptor.LoginInterceptor;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns("/community/insert");	
		}
	
}

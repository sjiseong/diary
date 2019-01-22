package com.inc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.inc.interceptor.AuthorityInterceptor;

@Configuration
public class ServletContext implements WebMvcConfigurer {

	@Autowired
	private AuthorityInterceptor authorityInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(authorityInterceptor).addPathPatterns("/").addPathPatterns("/user/mypage")
				.addPathPatterns("/diary/**");

	}

}

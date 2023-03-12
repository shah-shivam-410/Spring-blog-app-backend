package com.example.demo.config;

import org.h2.server.web.WebServlet;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.JwtTokenHelper;

@Configuration
public class CommonBeans {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtTokenHelper jwtTokenHelper() {
		return new JwtTokenHelper();
	}
	
	@Configuration
	public class WebConfiguration {
	    @Bean
	    ServletRegistrationBean h2servletRegistration(){
	        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
	        registrationBean.addUrlMappings("/console/*");
	        return registrationBean;
	    }
	}
	
}

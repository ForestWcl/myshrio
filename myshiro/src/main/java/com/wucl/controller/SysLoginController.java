package com.wucl.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sys")
public class SysLoginController {
	
	@RequestMapping("/login")
	public String loginForm(){
		return "login";
	}
	
	@RequestMapping("/loginHandler")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password){
		Subject subject = SecurityUtils.getSubject();
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			subject.login(token );
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "login";
		}
		return "index";
	}
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	@RequestMapping("/home1")
	public String home1(){
		return "home1";
	}
	@RequestMapping("/home2")
	public String home2(){
		return "home2";
	}
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "unauthorized";
	}
}

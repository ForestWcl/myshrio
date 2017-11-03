package com.wucl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wucl.vo.User;

@Controller
@RequestMapping("/sys")
public class SysLoginController extends FormAuthenticationFilter {
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
		String error = null;
		if(exceptionClassName!=null ){
			if(UnknownAccountException.class.getName().equals(exceptionClassName)){
				error="用户名不存在";
			}else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
				error="密码错误";
			}else if("randomCodeError".equals(exceptionClassName)){
				error="验证码输入错误";
			}else if(LockedAccountException.class.getName().equals(exceptionClassName)){
				error="改用户被禁用";
			}else{
				error="服务器未知错误";
			}
			request.setAttribute("error", error);
			Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getSession().getAttribute("user");
			if(user!=null){
				subject.logout();
			}
		}
		return "login";
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
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
}

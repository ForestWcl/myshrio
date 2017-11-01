package com.wucl.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.util.ResponseInfo;
import com.wucl.ssm.vo.User;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
	
	@Autowired
	private IUserService UserServiceImpl;
	
	@RequestMapping("/login")
	public String loginFrom(){
		return "jsp/login";
	}
	
	@RequestMapping("/dologin")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			//执行认证操作
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			request.setAttribute("msg", e.getMessage());
			return "jsp/login";
		}
		return "jsp/index";
		
	}
	
	@RequestMapping("/home")
	public String home(){
		return "jsp/test/home";
	}
	
	@RequestMapping(value="/getInfo",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getInfo(@RequestParam("id") String id){
		System.out.println("===>id:"+id);
		User user = UserServiceImpl.getUserById(id);
		List<User> li = new ArrayList<>();
		li.add(user);
		ResponseInfo info = new ResponseInfo(li);
		return info.toJson();
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "error/unauthorized";
	}
}

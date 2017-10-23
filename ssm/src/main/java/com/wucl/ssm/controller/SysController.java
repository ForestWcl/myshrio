package com.wucl.ssm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.vo.User;

@Controller
@RequestMapping("/sys")
public class SysController {
	
	@Autowired
	private IUserService UserServiceImpl;
	
	@RequestMapping("/loginFrom")
	public String loginFrom(){
		return "jsp/login";
	}
	
	@RequestMapping("/loginCheck")
	public String loginHandler(User user,Map<String,Object> model){
		User u = UserServiceImpl.loginCheck(user);
		String msg ="";
		if(u==null){
			msg="用户名和密码不正确！";
			model.put("msg", msg);
			return "jsp/login";
			
		}
		return "jsp/test/home";
	}
}

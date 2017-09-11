package com.wucl.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.util.ResponseInfo;
import com.wucl.ssm.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userServiceImpl;
	
	@RequestMapping
	public String getHelloWord(){
		return "jsp/test/home";
	}
	
	@RequestMapping(value="getUser",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getUser(int id,HttpServletResponse res){
		User user = userServiceImpl.getUserById(id);
		List<User> li = new ArrayList<>();
		li.add(user);
		ResponseInfo ri = new ResponseInfo(li);
		String json = ri.toJson();
		return json;
	}
}

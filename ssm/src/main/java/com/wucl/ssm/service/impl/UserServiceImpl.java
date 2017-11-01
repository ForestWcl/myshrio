package com.wucl.ssm.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

import com.wucl.ssm.mapper.UserMapper;
import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.vo.User;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	@RequiresRoles(value = { "admin" })
	public User getUserById(String id) {
		User user = userMapper.getUserById(id);
		return user;
	}

	@Override
	public User loginCheck(String username) {
		User u = userMapper.loginCheck(username);
		return u;
	}

}

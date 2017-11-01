package com.wucl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wucl.mapper.UserMapper;
import com.wucl.service.IUserService;
import com.wucl.vo.User;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserInfoByUsername(String username) {
		User user = userMapper.getUserInfoByUsername(username);
		return user;
	}

}

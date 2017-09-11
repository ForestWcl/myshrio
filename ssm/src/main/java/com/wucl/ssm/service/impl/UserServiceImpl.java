package com.wucl.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wucl.ssm.mapper.UserMapper;
import com.wucl.ssm.service.IUserService;
import com.wucl.ssm.vo.User;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User getUserById(int id) {
		User user = userMapper.getUserById(id);
		return user;
	}

}

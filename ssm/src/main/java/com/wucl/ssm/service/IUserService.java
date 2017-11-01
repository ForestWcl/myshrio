package com.wucl.ssm.service;

import com.wucl.ssm.vo.User;

public interface IUserService {

	public User getUserById(String username);
	/**
	 * 根据用户名查询用户是否存在
	 * @param principal
	 * @return	user
	 */
	public User loginCheck(String principal);
}

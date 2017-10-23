package com.wucl.ssm.service;

import com.wucl.ssm.vo.User;

public interface IUserService {

	public User getUserById(int id);
	/**
	 * 根据用户名查询用户是否存在
	 * @param user
	 * @return	user
	 */
	public User loginCheck(User user);
}

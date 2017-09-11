package com.wucl.ssm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wucl.ssm.vo.User;

@Repository
public interface UserMapper {

	public User getUserById(@Param("id") int id);
}

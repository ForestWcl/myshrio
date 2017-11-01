package com.wucl.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wucl.vo.User;

@Repository
public interface UserMapper {

	User getUserInfoByUsername(@Param("username")String username);

}

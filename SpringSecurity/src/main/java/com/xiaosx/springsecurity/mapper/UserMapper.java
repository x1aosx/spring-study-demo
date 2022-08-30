package com.xiaosx.springsecurity.mapper;

import com.xiaosx.springsecurity.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    @Select("SELECT * FROM `user` WHERE username = #{username}")
    User selectByUsername(String username);
}

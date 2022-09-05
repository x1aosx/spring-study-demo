package com.xiaosx.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosx.springsecurity.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


public interface UserMapper extends BaseMapper<User> {

}

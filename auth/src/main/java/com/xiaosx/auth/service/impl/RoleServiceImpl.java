package com.xiaosx.auth.service.impl;

import com.xiaosx.auth.entity.Role;
import com.xiaosx.auth.mapper.RoleMapper;
import com.xiaosx.auth.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaosx
 * @since 2022-10-292 21:16:34
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

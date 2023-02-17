package com.xiaosx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaosx.auth.entity.Menu;
import com.xiaosx.auth.entity.RoleMenu;
import com.xiaosx.auth.entity.UserRole;
import com.xiaosx.auth.mapper.MenuMapper;
import com.xiaosx.auth.mapper.RoleMenuMapper;
import com.xiaosx.auth.mapper.UserRoleMapper;
import com.xiaosx.auth.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaosx
 * @since 2022-10-292 21:16:34
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuMapper menuMapper;


    @Override
    public List<String> selectByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        // 查询用户拥有的所有角色
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        if (userRoles.size() == 0) {
            return new ArrayList<>();
        }
        List<Long> roleIdList = userRoles.stream().map(UserRole::getRoleId).toList();
        LambdaQueryWrapper<RoleMenu> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(RoleMenu::getRoleId, roleIdList);
        // 查询角色拥有的所有权限
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(queryWrapper1);
        List<Long> menuIdList = roleMenus.stream().map(RoleMenu::getMenuId).toList();
        LambdaQueryWrapper<Menu> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(Menu::getId, menuIdList);
        List<Menu> menus = menuMapper.selectList(queryWrapper2);
        return menus.stream().map(Menu::getPath).toList();
    }
}

package com.xiaosx.auth.service;

import com.xiaosx.auth.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaosx
 * @since 2022-10-292 21:16:34
 */
public interface IMenuService extends IService<Menu> {
    /*
     * @author x1aosx
     * @description 查询用户拥有的所有权限
     * @date 2022/10/13 18:40
     * @param userId: 用户id
     **/
    List<String> selectByUserId(Long userId);
}

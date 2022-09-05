package com.xiaosx.springsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaosx.springsecurity.mapper.UserMapper;
import com.xiaosx.springsecurity.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author x1aosx
 * @classname MybatisPlusTest.java
 * @description TODO
 * @createTime 2022/09/04 18:09
 */
@Slf4j
@SpringBootTest
public class MybatisPlusTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        // 查询用户list
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("xsx1");
        user.setPassword("test");
        int result = userMapper.insert(user);
        log.info(String.valueOf(result));
        log.info(String.valueOf(user));
    }

    /*
     * @author x1aosx
     * @description 通过id删除数据
     * @date 2022/9/5 14:25

     **/
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1566619001840939009L);
        log.info(String.valueOf(result));
    }

    /*
     * @author x1aosx
     * @description 根据map中的条件进行删除
     * @date 2022/9/5 14:33

     **/
    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Young");
        int result = userMapper.deleteByMap(map);
        log.info(String.valueOf(result));
    }

    @Test
    public void testDeleteByBatchIds() {
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        log.info(String.valueOf(result));
    }

    @Test
    public void testLambdaQueryWrapper() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Integer idStart = 10;
        Integer idEnd = 30;
        lambdaQueryWrapper.ge(true, User::getId, idStart)
                .le(true, User::getId, idEnd);
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        log.info(userList.toString());
    }

}

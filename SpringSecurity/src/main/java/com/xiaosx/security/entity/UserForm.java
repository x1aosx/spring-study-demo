package com.xiaosx.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author x1aosx
 * @classname UserForm.java
 * @description TODO
 * @createTime 2022/10/09 16:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    /*
    用户名
     */
    private String username;
    /*
    密码
     */
    private String password;
}

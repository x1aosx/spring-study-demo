package com.xiaosx.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaosx
 * @since 2022-10-291 21:56:33
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("username")
    private String username;

    @TableField("`password`")
    private String password;

    @TableField("`name`")
    private String name;
}

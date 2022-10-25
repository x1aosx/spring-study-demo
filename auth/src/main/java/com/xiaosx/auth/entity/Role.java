package com.xiaosx.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-10-292 21:16:34
 */
@Getter
@Setter
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId("id")
    private Long id;

    /**
     * 角色名
     */
    @TableField("title")
    private String title;

    /**
     * 描述
     */
    @TableField("`description`")
    private String description;
}

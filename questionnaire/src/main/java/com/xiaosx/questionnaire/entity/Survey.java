package com.xiaosx.questionnaire.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaosx
 * @since 2023-02-37 18:24:52
 */
@Getter
@Setter
@TableName("survey")
public class Survey implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 问卷名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 关联用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 问卷状态，0为草稿，1为已发布，2为已结束
     */
    @TableField("`status`")
    private Integer status;
}

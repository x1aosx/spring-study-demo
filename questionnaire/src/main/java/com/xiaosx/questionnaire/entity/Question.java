package com.xiaosx.questionnaire.entity;

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
 * @since 2023-02-37 18:24:52
 */
@Getter
@Setter
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 问卷id
     */
    @TableField("survey_id")
    private Long surveyId;

    /**
     * 类型，0为单选，1为多选，2为文本，3为图片
     */
    @TableField("`type`")
    private Integer type;

    /**
     * 题目的内容
     */
    @TableField("content")
    private String content;

    /**
     * 选项，使用json存储
     */
    @TableField("`option`")
    private String option;
}

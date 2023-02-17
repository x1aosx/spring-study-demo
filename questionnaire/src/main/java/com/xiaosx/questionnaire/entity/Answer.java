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
@TableName("answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 问卷id
     */
    @TableField("survey_id")
    private Long surveyId;

    /**
     * 题目id
     */
    @TableField("question_id")
    private Long questionId;

    /**
     * 答案
     */
    @TableField("content")
    private String content;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 回答时间
     */
    @TableField("answer_time")
    private LocalDateTime answerTime;
}

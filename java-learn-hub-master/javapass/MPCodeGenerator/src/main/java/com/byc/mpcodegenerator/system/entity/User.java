package com.byc.mpcodegenerator.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 微信用户唯一标识
     */
    @TableField("openid")
    private String openid;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 身份证号
     */
    @TableField("id_number")
    private String idNumber;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    @TableField("create_time")
    private LocalDateTime createTime;
}

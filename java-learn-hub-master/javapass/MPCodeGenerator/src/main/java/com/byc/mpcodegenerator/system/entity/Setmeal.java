package com.byc.mpcodegenerator.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("setmeal")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜品分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 套餐名称
     */
    @TableField("name")
    private String name;

    /**
     * 套餐价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 售卖状态 0:停售 1:起售
     */
    @TableField("status")
    private Integer status;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 修改人
     */
    @TableField("update_user")
    private Long updateUser;
}

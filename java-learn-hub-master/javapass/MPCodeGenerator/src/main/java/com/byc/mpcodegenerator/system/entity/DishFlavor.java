package com.byc.mpcodegenerator.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜品口味关系表
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("dish_flavor")
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜品
     */
    @TableField("dish_id")
    private Long dishId;

    /**
     * 口味名称
     */
    @TableField("name")
    private String name;

    /**
     * 口味数据list
     */
    @TableField("value")
    private String value;
}

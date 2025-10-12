package com.byc.mpcodegenerator.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 套餐菜品关系
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("setmeal_dish")
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 套餐id
     */
    @TableField("setmeal_id")
    private Long setmealId;

    /**
     * 菜品id
     */
    @TableField("dish_id")
    private Long dishId;

    /**
     * 菜品名称 （冗余字段）
     */
    @TableField("name")
    private String name;

    /**
     * 菜品单价（冗余字段）
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 菜品份数
     */
    @TableField("copies")
    private Integer copies;
}

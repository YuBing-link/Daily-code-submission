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
 * 订单明细表
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 菜品id
     */
    @TableField("dish_id")
    private Long dishId;

    /**
     * 套餐id
     */
    @TableField("setmeal_id")
    private Long setmealId;

    /**
     * 口味
     */
    @TableField("dish_flavor")
    private String dishFlavor;

    /**
     * 数量
     */
    @TableField("number")
    private Integer number;

    /**
     * 金额
     */
    @TableField("amount")
    private BigDecimal amount;
}

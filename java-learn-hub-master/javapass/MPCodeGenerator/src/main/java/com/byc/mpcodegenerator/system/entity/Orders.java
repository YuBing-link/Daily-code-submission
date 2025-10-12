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
 * 订单表
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField("number")
    private String number;

    /**
     * 订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款
     */
    @TableField("status")
    private Integer status;

    /**
     * 下单用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 地址id
     */
    @TableField("address_book_id")
    private Long addressBookId;

    /**
     * 下单时间
     */
    @TableField("order_time")
    private LocalDateTime orderTime;

    /**
     * 结账时间
     */
    @TableField("checkout_time")
    private LocalDateTime checkoutTime;

    /**
     * 支付方式 1微信,2支付宝
     */
    @TableField("pay_method")
    private Integer payMethod;

    /**
     * 支付状态 0未支付 1已支付 2退款
     */
    @TableField("pay_status")
    private Byte payStatus;

    /**
     * 实收金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 收货人
     */
    @TableField("consignee")
    private String consignee;

    /**
     * 订单取消原因
     */
    @TableField("cancel_reason")
    private String cancelReason;

    /**
     * 订单拒绝原因
     */
    @TableField("rejection_reason")
    private String rejectionReason;

    /**
     * 订单取消时间
     */
    @TableField("cancel_time")
    private LocalDateTime cancelTime;

    /**
     * 预计送达时间
     */
    @TableField("estimated_delivery_time")
    private LocalDateTime estimatedDeliveryTime;

    /**
     * 配送状态  1立即送出  0选择具体时间
     */
    @TableField("delivery_status")
    private Boolean deliveryStatus;

    /**
     * 送达时间
     */
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    /**
     * 打包费
     */
    @TableField("pack_amount")
    private Integer packAmount;

    /**
     * 餐具数量
     */
    @TableField("tableware_number")
    private Integer tablewareNumber;

    /**
     * 餐具数量状态  1按餐量提供  0选择具体数量
     */
    @TableField("tableware_status")
    private Boolean tablewareStatus;
}

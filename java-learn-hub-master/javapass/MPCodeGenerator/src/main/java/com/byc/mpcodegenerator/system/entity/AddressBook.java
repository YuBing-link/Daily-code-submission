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
 * 地址簿
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Getter
@Setter
@TableName("address_book")
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 收货人
     */
    @TableField("consignee")
    private String consignee;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 省级区划编号
     */
    @TableField("province_code")
    private String provinceCode;

    /**
     * 省级名称
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 市级区划编号
     */
    @TableField("city_code")
    private String cityCode;

    /**
     * 市级名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 区级区划编号
     */
    @TableField("district_code")
    private String districtCode;

    /**
     * 区级名称
     */
    @TableField("district_name")
    private String districtName;

    /**
     * 详细地址
     */
    @TableField("detail")
    private String detail;

    /**
     * 标签
     */
    @TableField("label")
    private String label;

    /**
     * 默认 0 否 1是
     */
    @TableField("is_default")
    private Boolean isDefault;
}

package com.nvoe.mybatisdemo.datauser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class emp {
    private  int empId;
    private String empName;
    private  String gender;
    private  String department;
    private  String position;
    private BigDecimal salary;
    private  String phone;
}

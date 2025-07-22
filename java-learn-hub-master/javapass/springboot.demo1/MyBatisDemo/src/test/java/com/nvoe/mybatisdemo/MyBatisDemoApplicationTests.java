package com.nvoe.mybatisdemo;

import com.nvoe.mybatisdemo.Mapper.EmpMapper;
import com.nvoe.mybatisdemo.datauser.emp;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MyBatisDemoApplicationTests {
    @Autowired
    private EmpMapper empMapper;
    @Test
    public void test(){
//        emp e=new emp();
//        e.setEmpId(12);
//        e.setEmpName("无敌了孩子");
//        e.setGender("男");
//        e.setDepartment("开发");
//        e.setPosition("组长");
//        e.setSalary(BigDecimal.valueOf(15000.1));
//        e.setPhone("16484521315");
//        empMapper.update(e);
//        emp e=empMapper.select(5);
        System.out.println( empMapper.select("emp_name","Tom"));
    }

}

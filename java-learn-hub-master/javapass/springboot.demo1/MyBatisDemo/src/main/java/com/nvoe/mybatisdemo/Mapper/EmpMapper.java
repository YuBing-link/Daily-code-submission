package com.nvoe.mybatisdemo.Mapper;

import com.nvoe.mybatisdemo.datauser.emp;
import org.apache.ibatis.annotations.*;

@Mapper
public  interface EmpMapper {

    @Delete("delete from emp where emp_id=#{id}")
     void delete(Integer id);
    @Options(keyProperty = "empId", useGeneratedKeys = true)
    @Insert("insert into emp(emp_name, gender, department, position, salary, phone) value (#{empName},#{gender},#{department},#{position},#{salary},#{phone})")
    void insert(emp emp);
    @Update("update emp set emp_name=#{empName},gender= #{gender},department=#{department},position=#{position},salary=#{salary},phone=#{phone} where emp_id=#{empId}")
    void  update(emp emp);
//    @Select("select emp_id , emp_name , gender, department, position, salary, phone from emp where ${first}=#{second}")
    emp select(Object first,Object second);


}

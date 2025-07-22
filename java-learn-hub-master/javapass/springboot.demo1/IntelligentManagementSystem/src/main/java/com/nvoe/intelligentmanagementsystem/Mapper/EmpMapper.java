package com.nvoe.intelligentmanagementsystem.Mapper;

import com.nvoe.intelligentmanagementsystem.POJO.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    @Select("select * from emp")
    List<Emp> list(Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void save(Emp emp);
//    @Select("select count(id) from emp")
//    Integer count();
//    @Select("select * from emp limit #{page},#{pageSize}")
//    List<Emp> page(Integer page, Integer pageSize);
}

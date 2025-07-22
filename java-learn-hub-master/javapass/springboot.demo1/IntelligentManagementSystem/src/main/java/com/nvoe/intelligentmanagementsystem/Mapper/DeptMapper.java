package com.nvoe.intelligentmanagementsystem.Mapper;

import com.nvoe.intelligentmanagementsystem.POJO.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.xml.transform.Result;
import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> getDepts();
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);
    @Insert("insert into dept(name, create_time, update_time) value (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
}

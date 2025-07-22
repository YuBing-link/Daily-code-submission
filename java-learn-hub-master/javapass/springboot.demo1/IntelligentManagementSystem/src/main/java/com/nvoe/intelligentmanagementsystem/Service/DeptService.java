package com.nvoe.intelligentmanagementsystem.Service;

import com.nvoe.intelligentmanagementsystem.POJO.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getDepts();

    void delete(Integer id);

    void save(Dept dept);
}

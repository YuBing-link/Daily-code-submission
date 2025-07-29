package com.nvoe.intelligentmanagementsystem.Service.impl;

import com.nvoe.intelligentmanagementsystem.Mapper.DeptMapper;
import com.nvoe.intelligentmanagementsystem.POJO.Dept;
import com.nvoe.intelligentmanagementsystem.Service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceimpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> getDepts() {
        return deptMapper.getDepts();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void save(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}

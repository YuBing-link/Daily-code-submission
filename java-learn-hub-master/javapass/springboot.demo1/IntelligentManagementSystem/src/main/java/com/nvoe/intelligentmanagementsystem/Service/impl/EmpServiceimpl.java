package com.nvoe.intelligentmanagementsystem.Service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nvoe.intelligentmanagementsystem.Mapper.EmpMapper;
import com.nvoe.intelligentmanagementsystem.POJO.Emp;
import com.nvoe.intelligentmanagementsystem.POJO.PageBean;
import com.nvoe.intelligentmanagementsystem.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceimpl implements  EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public void up(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.up(emp);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end)
    {
//        Integer count = empMapper.count();
//        Integer pageNum = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(pageNum, pageSize);
//
//        return new PageBean(count,empList);
        PageHelper.startPage(page,pageSize);
        List<Emp> list = empMapper.list(pageSize,name,gender,begin,end);
        Page<Emp> p=(Page<Emp>)list ;

        return new PageBean(p.getPages(),p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }
    
    }

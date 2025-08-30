package com.nvoe.intelligentmanagementsystem.Service;

import com.nvoe.intelligentmanagementsystem.POJO.Emp;
import com.nvoe.intelligentmanagementsystem.POJO.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {



    void up(Emp emp);

    PageBean page(Integer page, Integer pageSize, String name, Short gender,
                  LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp login(Emp emp);

}

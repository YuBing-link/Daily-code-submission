package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Dept;
import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.Service.impl.DeptServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    DeptServiceimpl deptService;

    @GetMapping
    public Result list(){
        log.info("查询部门全部信息");
        return Result.success(deptService.getDepts());
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("根据id删除部门:{}",id);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Dept dept){
        deptService.save(dept);
        log.info("新增部门");
        return Result.success();
    }

}

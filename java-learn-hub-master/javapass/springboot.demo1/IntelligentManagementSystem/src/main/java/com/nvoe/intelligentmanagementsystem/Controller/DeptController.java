package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Dept;
import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.Service.impl.DeptServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理控制器类
 * 提供部门信息的查询、删除和新增功能
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    DeptServiceimpl deptService;

    /**
     * 查询所有部门信息
     * @return 包含所有部门信息的结果对象
     */
    @GetMapping
    public Result list(){
        log.info("查询部门全部信息");
        return Result.success(deptService.getDepts());
    }

    /**
     * 根据ID删除部门信息
     * @param id 部门ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("根据id删除部门:{}",id);
        return Result.success();
    }

    /**
     * 新增部门信息
     * @param dept 部门对象
     * @return 新增结果
     */
    @PostMapping
    public Result save(@RequestBody Dept dept){
        deptService.save(dept);
        log.info("新增部门");
        return Result.success();
    }

}

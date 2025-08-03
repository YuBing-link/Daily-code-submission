package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Emp;
import com.nvoe.intelligentmanagementsystem.POJO.PageBean;
import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.Service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理控制器类
 * 提供员工信息的分页查询、删除、新增和更新功能
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;

    /**
     * 分页查询员工信息
     * @param page 页码，默认值为1
     * @param pageSize 每页记录数，默认值为10
     * @param name 员工姓名，用于模糊查询
     * @param gender 员工性别，1表示男，2表示女
     * @param begin 入职日期起始时间
     * @param end 入职日期结束时间
     * @return 分页查询结果
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("page:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);


        return Result.success(pageBean);
    }

    /**
     * 根据ID列表删除员工信息
     * @param ids 员工ID列表
     * @return 删除结果
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("ids:{}",ids);
        empService.delete(ids);
        return Result.success();

    }

    /**
     * 新增员工信息
     * @param emp 员工对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 更新员工信息
     * @param emp 员工对象
     * @return 更新结果
     */
    @PutMapping
    public Result up(@RequestBody Emp emp) {
        log.info("emp:{}",emp);
        empService.up(emp);
        return Result.success();
    }

}

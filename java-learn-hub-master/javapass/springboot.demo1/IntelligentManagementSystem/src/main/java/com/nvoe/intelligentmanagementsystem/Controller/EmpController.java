package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Emp;
import com.nvoe.intelligentmanagementsystem.POJO.PageBean;
import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.Service.EmpService;
import com.nvoe.intelligentmanagementsystem.anno.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("page:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);


        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("ids:{}",ids);
        empService.delete(ids);
        return Result.success();

    }
    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @Log
    @PutMapping
    public Result up(@RequestBody Emp emp) {
        log.info("emp:{}",emp);
        empService.up(emp);
        return Result.success();
    }

}

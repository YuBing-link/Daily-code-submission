package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    /**
     * 新增套餐
     */
    @PostMapping
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result<String> save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐:{}",setmealDTO);
        setmealService.save(setmealDTO);

        return Result.success();
    }
    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询:{}",setmealPageQueryDTO);
        return Result.success(setmealService.PageQuery(setmealPageQueryDTO));
    }

    /**
     * 删除套餐
     */
    @DeleteMapping
    @ApiOperation("删除套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result<String> deleteBatch(@RequestParam List<Long> ids){
        log.info("删除套餐id:{}",ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }
    /**
     * 根据id查询套餐
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getSetmealById(@PathVariable Long id){
        log.info("查询套餐id:{}",id);
        SetmealVO setmealVO = setmealService.getSetmealById(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐
     */
    @PutMapping
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result<SetmealDTO> update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐");
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 起售停售
     */
    @PostMapping("status/{status}")
    @ApiOperation("起售停售")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result<String> startOrStop(@PathVariable Integer status,Long id){
        log.info("起售停售:{}",id);
        setmealService.startOrStop(status,id);
        return Result.success();
    }
}
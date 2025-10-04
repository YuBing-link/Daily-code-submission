package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report")
@Api(tags = "数据统计接口")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 订单统计接口
     * @return
     */
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额数据统计")
    public Result<TurnoverReportVO> getTurnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("订单统计：{}到{}", begin, end);
        TurnoverReportVO data = reportService.getTurnoverStatistics(begin, end);

        return Result.success(data);
    }
    /**
     * 用户统计接口
     * @return
     */
    @GetMapping("/userStatistics")
    @ApiOperation("用户数据统计")
    public Result<UserReportVO> getUserStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("用户统计：{}到{}", begin, end);
        UserReportVO data = reportService.getUserStatistics(begin, end);
        return Result.success(data);
    }
    /**
     * 订单统计接口
     */
    @GetMapping("/ordersStatistics")
    @ApiOperation("订单数据统计")
    public Result<OrderReportVO> getOrdersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("订单统计：{}到{}", begin, end);
        OrderReportVO data = reportService.getBusinessData(begin, end);
        return Result.success(data);
    }
    /**
     * 查询销量排名top10接口
     */
    @GetMapping("/top10")
    @ApiOperation("查询销量排名top10")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("查询销量排名top10：{}到{}", begin, end);
        SalesTop10ReportVO data = reportService.getSalesTop10(begin, end);
        return Result.success(data);
    }
    /**
     * 导出营业数据
     * */
    @GetMapping("/export")
    @ApiOperation("导出营业数据")
    public void exportBusinessData(HttpServletResponse response){

        reportService.exportBusinessData(response);

    }
}

package com.sky.service.impl;

import com.sky.mapper.OrdersMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.service.WorkspaceService;
import com.sky.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkspaceService workspaceService;
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> beginlist = new ArrayList<>();
        beginlist.add(begin);
        while(!begin.equals(end)){
            begin=begin.plusDays(1);
            beginlist.add(begin);
        }
        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : beginlist) {
            //查询日期对应的营业额
            //SELECT SUM(amount) FROM orders WHERE order_time >= begin AND order_time < end
            Double turnover = ordersMapper.sumByOrderTime(LocalDateTime.of(date, LocalTime.MIN),LocalDateTime.of(date, LocalTime.MAX));
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }

        return TurnoverReportVO.builder()
                .dateList(StringUtils.join(beginlist,","))
                .turnoverList(StringUtils.join(turnoverList,","))
                .build();
    }

    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> beginlist = new ArrayList<>();
        beginlist.add(begin);
        while(!begin.equals(end)){
            begin=begin.plusDays(1);
            beginlist.add(begin);
        }
        List<Integer> totalUserList = new ArrayList<>();
        List<Integer> newUserList = new ArrayList<>();
        for (LocalDate date : beginlist) {
            //查询日期对应的用户数量
            //SELECT COUNT(id) FROM user WHERE create_time >= begin AND create_time < end
            Integer totalUser = userMapper.countByCreateTime(LocalDateTime.of(date, LocalTime.MIN));
            totalUser = totalUser == null ? 0 : totalUser;
            Integer newUser = userMapper.countByNewCreateTime(LocalDateTime.of(date, LocalTime.MIN),LocalDateTime.of(date, LocalTime.MAX));
            newUser = newUser == null ? 0 : newUser;
            totalUserList.add(totalUser);
            newUserList.add(newUser);
        }

        return UserReportVO.builder()
                .dateList(StringUtils.join(beginlist,","))
                .totalUserList(StringUtils.join(totalUserList,","))
                .newUserList(StringUtils.join(newUserList,","))
                .build();
    }

    @Override
    public OrderReportVO getBusinessData(LocalDate begin, LocalDate end) {
        List<LocalDate> beginlist = new ArrayList<>();
        //订单总数
        Integer totalOrderCount = ordersMapper.countByTime(LocalDateTime.of(begin, LocalTime.MIN),LocalDateTime.of(end, LocalTime.MAX));
        //有效订单总数
        Integer totalValidOrderCount = ordersMapper.countByValidTime(LocalDateTime.of(begin, LocalTime.MIN),LocalDateTime.of(end, LocalTime.MAX));
        beginlist.add(begin);
        while(!begin.equals(end)){
            begin=begin.plusDays(1);
            beginlist.add(begin);
        }
        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        for (LocalDate date : beginlist) {
            //查询日期对应的订单数
            Integer orderCount = ordersMapper.countByTime(LocalDateTime.of(date, LocalTime.MIN),LocalDateTime.of(date, LocalTime.MAX));
            orderCount = orderCount == null ? 0 : orderCount;
            orderCountList.add(orderCount);
            //查询日期对应的有效订单数
            Integer validOrderCount = ordersMapper.countByValidTime(LocalDateTime.of(date, LocalTime.MIN),LocalDateTime.of(date, LocalTime.MAX));
            validOrderCount = validOrderCount == null ? 0 : validOrderCount;
            validOrderCountList.add(validOrderCount);
        }



        return OrderReportVO.builder()
                .dateList(StringUtils.join(beginlist,","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(totalValidOrderCount)
                .orderCountList(StringUtils.join(orderCountList,","))
                .validOrderCountList(StringUtils.join(validOrderCountList,","))
                .orderCompletionRate(totalValidOrderCount.doubleValue() / totalOrderCount)
                .build();
    }

    @Override
    public SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end) {
        //统计销量前10
        //1.通过订单id查到订单时间
        //2.通过订单id查到商品名称和数量
        List<String> nameList = new ArrayList<>();
        List<String> numberList = new ArrayList<>();
        List<SalesTop10ReportVO> salesTop10 = ordersMapper.getSalesTop10(LocalDateTime.of(begin, LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        for (SalesTop10ReportVO salesTop10ReportVO : salesTop10) {
            if (salesTop10ReportVO != null) {
                nameList.add(salesTop10ReportVO.getNameList());
                numberList.add(salesTop10ReportVO.getNumberList());
            }
        }
        return SalesTop10ReportVO.builder()
                .nameList(StringUtils.join(nameList,","))
                .numberList(StringUtils.join(numberList,","))
                .build();

    }

    @Override
    public void exportBusinessData(HttpServletResponse response) {
        LocalDate beginData = LocalDate.now().minusDays(30);
        LocalDate endData = LocalDate.now();
        LocalDateTime begin = LocalDateTime.of(beginData, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(endData, LocalTime.MAX);
        BusinessDataVO businessData = workspaceService.getBusinessData(begin, end);
        InputStream in= this.getClass().getClassLoader().getResourceAsStream("运营数据报表模板.xlsx");
        try {
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("Sheet1");
            sheet.getRow(1).getCell(1).setCellValue(beginData + "至" + endData);
            XSSFRow row = sheet.getRow(3);
            row.getCell(2).setCellValue(businessData.getTurnover());
            row.getCell(4).setCellValue(businessData.getValidOrderCount());
            row.getCell(6).setCellValue(businessData.getOrderCompletionRate());
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessData.getNewUsers());
            row.getCell(4).setCellValue(businessData.getUnitPrice());
            for(int i = 0; i < 30; i++){
                row = sheet.getRow(7+i);
                BusinessDataVO businessData1 = workspaceService.getBusinessData(LocalDateTime.of(beginData.plusDays(i), LocalTime.MIN), LocalDateTime.of(beginData.plusDays(i), LocalTime.MAX));
                row.getCell(1).setCellValue(beginData.plusDays(i) + "");
                row.getCell(2).setCellValue(businessData1.getTurnover());
                row.getCell(3).setCellValue(businessData1.getValidOrderCount());
                row.getCell(4).setCellValue(businessData1.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData1.getUnitPrice());
                row.getCell(6).setCellValue(businessData1.getNewUsers());
            }
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);
            out.flush();
            out.close();
            excel.close();
            in.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

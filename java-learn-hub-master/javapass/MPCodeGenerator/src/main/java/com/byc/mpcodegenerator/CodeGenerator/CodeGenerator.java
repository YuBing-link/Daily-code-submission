package com.byc.mpcodegenerator.CodeGenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
public class CodeGenerator {
    public static void main(String[] args) {
        // 1. 配置数据库连接
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/sky_take_out", "root","40475625" )
                // 2. 全局配置
                .globalConfig(builder -> {
                    builder.author("byc") // 设置作者
                            .outputDir(System.getProperty("user.dir") + "/src/main/java") // 输出路径（项目的 java 目录）
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .disableOpenDir();
                })
                // 3. 包配置
                .packageConfig(builder -> {
                    builder.parent("com.byc.mpcodegenerator") // 父包名
                            .moduleName("system") // 子模块名（可选）
                            .entity("entity") // 实体类包名
                            .mapper("mapper") // Mapper 接口包名
                            .service("service") // Service 接口包名
                            .serviceImpl("service.impl") // Service 实现类包名
                            .controller("controller") // Controller 包名
                            .xml("mapper.xml") // Mapper XML 路径（默认 resources/mapper/xml）
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper")
                            ); // XML 输出路径
                })
                // 4. 策略配置
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok() // 开启 Lombok
                            .enableTableFieldAnnotation() // 字段添加注解（@TableField）
                            .logicDeleteColumnName("deleted").enableFileOverride() // 逻辑删除字段
                            // Controller 策略
                            .controllerBuilder()
                            .enableRestStyle() // 开启 RestController 风格
                            .enableHyphenStyle().enableFileOverride() // 路径驼峰转连字符（如 userInfo -> user-info）
                            // Service 策略
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // Service 接口名格式
                            .formatServiceImplFileName("%sServiceImpl").enableFileOverride() // Service 实现类名格式
                            .mapperBuilder().enableFileOverride(); // Mapper 接口名格式

                })
                // 5. 模板引擎配置（Velocity）
                .templateEngine(new VelocityTemplateEngine())
                // 6. 执行生成
                .execute();
    }
}
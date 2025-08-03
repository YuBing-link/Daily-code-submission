package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Emp;
import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.Service.EmpService;
import com.nvoe.intelligentmanagementsystem.utils.JwtUtils.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    private final EmpService empService;

    @Autowired
    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        try {

            Emp e= empService.login(emp);
            if (e != null) {
                // 登录成功，生成JWT令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", e.getId());
                claims.put("username", e.getUsername());
                claims.put("name", e.getName());
                String token = Jwt.generateJwt(claims);
                log.info("登录成功，生成的JWT令牌: {}", token);
                return Result.success(token);
            }
            log.warn("登录失败: 用户名或密码错误");
            return Result.error("用户名或密码错误");
        } catch (Exception ex) {
            log.error("登录过程中发生异常: ", ex);
            return Result.error("系统错误，请稍后再试");
        }
    }
}
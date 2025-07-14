package com.nvoe.testdemo;

import com.nvoe.testdemo.user.usermsg;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class DemoTEST {
    @RequestMapping("/hello")
    public String hello(@RequestParam(name="name",required = false) String username , Integer age) {
        System.out.println(username+","+age);
        return "hello";
    }

    @RequestMapping("/simmsg")
    public String simmsg(usermsg user) {
        System.out.println(user);
        return "ok";
    }
    @RequestMapping("/hobby" )
    public String hobby(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "ok";
    }
    @RequestMapping("/list")
    public String list(List<String> hobby) {
        System.out.println(hobby);
        return "ok";
    }
    @RequestMapping("/data")
    public String data( LocalDate date) {
        System.out.println(date);
        return "ok";
    }
    @RequestMapping("/josnsimmsg")
    public String josnsimmsg(@RequestBody usermsg user) {
        System.out.println(user);
        return "ok";
    }
    @RequestMapping("/{id}/{name}")
    public String PATH(@PathVariable String id, @PathVariable String name) {
        System.out.println(id+","+name);
        return id+name;
    }
}










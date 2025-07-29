package com.nvoe.intelligentmanagementsystem.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {

    public long total;
    public List<Emp> rows;

}

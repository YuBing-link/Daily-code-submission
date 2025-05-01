package P5_1.Movie_System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    private String name;
    private String actor;
    private double price;
    public String toString() {
        return "电影名"+name+" 主演"+actor+" 价格"+price;
    }
}

package P5_1.Movie_System;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MovieFunction {
    private Scanner sc=new Scanner(System.in);
    private static ArrayList<Movie> movies = new ArrayList<>();

    public void start() {
        System.out.println("_______________________________________");
        System.out.println("1.输入电影");
        System.out.println("2.输入查询电影名");
        System.out.println("3.封杀");
        System.out.println("4.下架电影");
        System.out.println("5.展示电影");
        System.out.println("6.退出");
        System.out.println("_______________________________________");
        while (true) {
            switch(sc.next()){
                case "1"-> inputMovie();
                case "2" -> System.out.println(queryMovie());
                case "3"-> ban();
                case "4"-> takeoff();
                case "5"->showAllMovie();
                case "6"-> {return;}
                default-> System.out.println("输入错误");
            }
        }


    }

    private void showAllMovie() {
        for (int i = 0;i<movies.size();i++){
            System.out.println(movies.get(i));
        }
    }

    private void takeoff() {

        if(movies.isEmpty()){
            System.out.println("电影为空，请输入电影后尝试");
            return;
        }
        System.out.println("输入下架的电影");
        String name = sc.next();
        boolean a=false;
        Iterator<Movie> it=movies.iterator();
        while(it.hasNext()){
            Movie m=it.next();
            if(m.getName().equals(name)){
                it.remove();
                a=true;
            }

        }
        if(a) System.out.println("下架成功");
    }

    private void ban() {
        if(movies.isEmpty()){
            System.out.println("电影为空，请输入电影后尝试");
            return;
        }
        System.out.println("输入封杀的电影明星");
        String name = sc.next();
        boolean a=false;
        Iterator<Movie> it=movies.iterator();
        while (it.hasNext()){
            Movie m=it.next();
            if(m.getName().contains(name)){
                it.remove();
                a=true;
            }
        }
        if(a) System.out.println("封杀成功");
    }

    private Movie queryMovie() {

        if(movies.isEmpty()){
            System.out.println("电影为空，请输入电影后尝试");
            return null;
        }
        System.out.println("请输入查询的电影名");
        String name = sc.next();
        for(Movie movie:movies){
            if(movie.getName().equals(name)){
                return movie;
            }
        }
        return null;
    }

    private void inputMovie() {
        Movie m = new Movie();
        System.out.println("输入电影名");
        m.setName(sc.next());
        System.out.println("输入主演");
        m.setActor(sc.next());
        System.out.println("输入电影价格");
        m.setPrice(sc.nextDouble());
        movies.add(m);
        System.out.println("上架成功");
    }


}

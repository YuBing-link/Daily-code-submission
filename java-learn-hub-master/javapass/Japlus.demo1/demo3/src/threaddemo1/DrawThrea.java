package threaddemo1;

import java.util.Scanner;

public class DrawThrea extends Thread{
    private Accout t;
    private String name;
    public DrawThrea(String name, Accout t) {
        super(name);  // 使用setName方法明确设置线程名称
        this.t = t;
    }
    @Override
    public void run() {
        t.DrawMoney(10000);
    }
}

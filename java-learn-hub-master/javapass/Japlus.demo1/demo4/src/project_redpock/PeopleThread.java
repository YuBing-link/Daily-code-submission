package project_redpock;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.List;
public class PeopleThread implements Runnable {
    private List<Double> redPocket;
    private static double[] r = new double[101];

    public PeopleThread(List<Double> list) {
        this.redPocket = list;
    }


    @Override
    public void run() {
        Random random = new Random();


        while (true) {
            synchronized (redPocket) {
                if (redPocket.size() == 0) break;
                int x = random.nextInt(redPocket.size());
                if (redPocket.get(x) == 0) continue;
                double money = random.nextDouble(redPocket.get(x) + 1);
                int w = Integer.valueOf(Thread.currentThread().getName().replace("员工", ""));
                if (redPocket.get(x) - money <= 0) {
                    redPocket.set(x, 0.0);

                    // System.out.println(Thread.currentThread().getName()+"抢到了"+redPocket.get(x)+"元");
                    r[w] += redPocket.get(x);
                    redPocket.remove(x);

                } else {
                    redPocket.set(x, redPocket.get(x) - money);
                    r[w] += money;
                    // System.out.println(Thread.currentThread().getName()+"抢到了"+money+"元");
                }

            }
        }

    }

    public static double[] getR() {
        return r;
    }
}





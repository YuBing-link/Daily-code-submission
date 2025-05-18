package project_redpock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        List<Double> list = redpocket();
        for (int i = 1; i <=100 ; i++) {
          new Thread(new PeopleThread(list),"员工"+i).start();
        }
        double[] r=PeopleThread.getR();
        double max = 0;
        for (int i = 1; i <=100 ; i++)
            if(r[i]!=0){
                if(max<r[i])max=r[i];
                System.out.println("员工"+i+"一共抢到"+r[i]+"元");
            }

        System.out.println("抢到最大金额"+max);

        }



    public static List<Double> redpocket() {
        List<Double> red = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i <160 ; i++) {
            red.add(random.nextDouble(30)+1.0);
        }
        for (int i = 0; i <40;i++){
            red.add(random.nextDouble(69)+31.0);
        }
        return red;
    }
}









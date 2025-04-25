package demo;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入存钱数:");
            double m = sc.nextDouble();
            Card.bablance += m;
            Card w = Card.wp();
            System.out.println("请输入消费金额:");
            double m2 = sc.nextDouble();
            w.withdraw(m2);
            System.out.println("剩余金额："+Card.bablance);
        }
    }


}

import java.util.Scanner;

public class Pol2_3_23 {
    public static void pl1() {
        Scanner w1 = new Scanner(System.in);
        System.out.println("成绩：");
        int a = w1.nextInt();
        if (a >= 90) {
            System.out.println("绩点：4.0");
        } else if (a >= 80) {
            System.out.println("绩点：3.5");
        } else if (a >= 70) {
            System.out.println("绩点：3.0");
        } else if (a >= 60) {
            System.out.println("绩点：2.5");
        } else {
            System.out.println("绩点：0.0");
        }
    }
    public static void pl2() {
        System.out.println("输入阶乘：");
        Scanner w1 = new Scanner(System.in);
        int a = w1.nextInt();
        int b=1;
        for (int i = 1; i <= a; i++) {
            b = b * i;
        }
        System.out.println(b);
    }
    public static void pl3() {
        Scanner w1 = new Scanner(System.in);
        System.out.println("成绩：");
        int a = w1.nextInt();
        if (a >= 90) {
            System.out.println("五星");
        } else if (a >= 80) {
            System.out.println("四星");
        } else if (a >= 70) {
            System.out.println("三星");
        } else if (a >= 60) {
            System.out.println("二星");
        } else {
            System.out.println("无星");
        }
    }
    public static void pl4() {
        int j=0;
        for (int i = 1; i <= 100; i++) {
            if (i % 6 == 0) {
                System.out.print(i+" ");
                j++;
            }
        }
        System.out.println("共"+j+"个");
    }
    public static void pl5() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();

        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<10-i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] arg) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(j+"*"+i+"="+j*i+" ");
            }
            System.out.println();
        }



    }
}

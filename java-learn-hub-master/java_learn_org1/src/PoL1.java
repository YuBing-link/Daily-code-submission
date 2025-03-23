import java.util.Scanner;

public class PoL1 {
    public static void opi(){
        Scanner w1 = new Scanner(System.in);
        System.out.println("请输入第一个整数：");
        int a = w1.nextInt();
        System.out.println("请输入第二个整数：");
        int b = w1.nextInt();
        System.out.println("两数相加结果："+(a+b));
        System.out.println("两数相减结果："+(a-b));
        System.out.println("两数相乘结果："+(a*b));
        System.out.println("两数相除结果："+(a/b));
        System.out.println("两数相除余数："+(a%b));


    }
    public static void opis(){
        Scanner w1 = new Scanner(System.in);
        System.out.println("请输入第一个整数：a");
        int a = w1.nextInt();
        System.out.println("请输入第二个整数：b");
        int b = w1.nextInt();
        if (a>=b){
            System.out.println("a>=b"+false);
        }
        if (a<=b){
            System.out.println("a<=b"+true);
        }
        if (a==b){
            System.out.println("a==b"+false);
        }
        if (a!=b){
            System.out.println("a!=b"+true);
        }
        if (a>b){
            System.out.println("a>b"+false);
        }
        if (a<b){
            System.out.println("a<b"+true);
        }

    }

    public static void lsiw(int a){

        if(a%400==0||a%100!=0&&a%4==0){

            System.out.println("闰年");
        }

    }

        public static void main(String[]arg)
        {
            Scanner w1 = new Scanner(System.in);
            System.out.println("输入一个三位数:");
            int a = w1.nextInt();
            System.out.println("和："+(a/100+a/10%10+a%10));

        }




    }


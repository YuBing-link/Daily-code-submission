package P4_27.Generics;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        int a=list.get(0);
        System.out.println(a);
        int b1=15154;
        int b2 =15154;
        Integer a1=157;
        Integer a2 = 157;
        System.out.println(a1==a2);
        String j=a1.toString();
        System.out.println(j+15);
        int a5=Integer.valueOf(j);
        System.out.println(a5+14);
    }

    public static void go(ArrayList<?extends Car> cars){

    }

}

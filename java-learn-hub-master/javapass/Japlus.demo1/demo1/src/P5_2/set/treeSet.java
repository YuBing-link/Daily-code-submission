package P5_2.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class treeSet {
    public static void main(String[] args) {
        People p1=new People("老大", 28, 2000.0);
        People p2=new People("老二", 18, 1500.0);
        People p3=new People("老三", 20, 1501.0);
        People p4=new People("老四", 18, 1000.0);
        Set<People> set = new TreeSet<>(new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return Double.compare(o2.getPrice(),o1.getPrice());
            }
        });
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        System.out.println(set);

    }


}

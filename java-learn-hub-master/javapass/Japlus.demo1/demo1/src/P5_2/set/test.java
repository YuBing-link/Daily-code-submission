package P5_2.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class test {
    public static void main(String[] args) {
        Set<String> set=new HashSet<>();
        set.add("java");
        set.add("4");
        set.add("45xasxasz");
        set.add("1");

        set.add("3");
        set.add("7");
        set.add("5");

        System.out.println(set);
        //
        Set<Double> set1 = new TreeSet<>();
        set1.add(0.0);
        set1.add(0.1);
        set1.add(1.1);
        set1.add(0.2);
        set1.add(5.0);
        set1.add(0.3);
        set1.add(-0.10);
        set1.add(-0.20);
        set1.add(1.1);
        set1.add(0.2);
        set1.add(5.0);
        set1.add(0.3);
        System.out.println(set1);
        System.out.println(set1.hashCode());
        System.out.println(set.hashCode());
    }
}

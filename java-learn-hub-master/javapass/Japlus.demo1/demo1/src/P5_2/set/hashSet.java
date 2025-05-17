package P5_2.set;
import java.util.Set;
import java.util.HashSet;

public class hashSet {
    public static void main(String[] args) {
        People p1=new People("老我", 28, 2000.0);
        People p2=new People("老三", 20, 1500.0);
        People p3=new People("老三", 20, 1000.0);
        People p4=new People("老四", 18, 1000.0);
        Set<People> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        System.out.println(set);
    }
}

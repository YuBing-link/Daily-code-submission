package P5_2.set;

import java.util.LinkedHashSet;
import java.util.Set;

public class linKedHashSet {
    public static void main(String[] args) {
        Set<String> set=new LinkedHashSet<String>();
        set.add("5");
        set.add("1");
        set.add("7");
        set.add("2");
        set.add("4");
        System.out.println(set);
    }
}

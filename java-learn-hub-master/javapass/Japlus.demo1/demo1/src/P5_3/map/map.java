package P5_3.map;

import java.util.*;
import java.util.function.BiConsumer;

import static java.sql.Types.NULL;

public class map {
    public static void main(String[] args) {
        Map<String,Integer> m1 = new TreeMap<>();
        m1.put("1", 1);
        m1.put("2", 3);
        m1.put("4", 3);
        m1.put("5", 3);
        m1.put("3", 3);

//        m1.put(null,null);
        System.out.println(m1);
        Map<String, Integer> m2 = new LinkedHashMap<>();

        m2.put("4", 4);
        m2.put("5", 3);
        m2.put("1", 4);
        m2.put("2", 3);
        m2.put("3", 3);
        System.out.println(m2);
        Map<String,Integer> m=new HashMap<>();
        m.put("1",1);
        m.put("2", 1);
        m.put("3", 3);
        m.put("4", 2);
        m.put("1", 6);
        m.put(null,null);
        System.out.println(m);
        System.out.println(m.put("p",500));
        System.out.println(m.size());
        System.out.println(m.isEmpty());
        System.out.println(m.get("1"));
        System.out.println(m.remove("1"));
        System.out.println(m.containsKey("2"));
        System.out.println(m.containsValue(2));
        Set<String> set = m.keySet();
        for (String key : set){
            System.out.println(key+"="+m.get(key));
        }

        Collection<Integer> values = m.values();
        for(Integer v : values){
            System.out.println(v);
        }
        Set<Map.Entry<String, Integer>> set1 = m.entrySet();
        Set<Map.Entry<String, Integer>> set2=m.entrySet();
        for(Map.Entry<String, Integer> s:m.entrySet()){
            System.out.println(s.getKey()+"="+s.getValue());
        }
        m.forEach((String s,Integer i)-> System.out.println(s+i));
    }
}

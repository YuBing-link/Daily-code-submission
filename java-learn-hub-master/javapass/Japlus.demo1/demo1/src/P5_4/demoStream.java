package P5_4;

import java.util.*;
import java.util.stream.Stream;

public class demoStream {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        Collection<String> data1 = new ArrayList<>();
        Stream<String> s1 =data1.stream();
        Stream<String> s2 =data.stream();
        Map<String, Object> map = new HashMap<>();
        Stream<String> s3 = map.keySet().stream();
        Stream<Object> s4 = map.values().stream();
        Stream<Map.Entry<String, Object>> s = map.entrySet().stream();
        String[] names = {
            "1","2","3"};
        Stream<String> s5 = Arrays.stream(names);
        System.out.println(s5.count());
        Stream<String> names1 = Stream.of(names);
        Stream<Object> m =Stream.of("1",2,8);
        System.out.println(m.count());


    }

}

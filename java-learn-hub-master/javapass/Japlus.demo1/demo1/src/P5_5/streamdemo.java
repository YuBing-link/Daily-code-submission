package P5_5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class streamdemo {
    public static void main(String[] args) {
          List<String> list = new ArrayList<>();
          list.add("ab");
          list.add("bc");
          list.add("ca");
          list.add("di");
          list.add("de");

          list.stream().filter(s->s.startsWith("a")).forEach(System.out::println);
          List<Double> scores=new ArrayList<>();
          scores.add(0.0);
          scores.add(99.1);
          scores.add(88.1);
          scores.add(90.1);
        scores.add(99.1);
        scores.add(88.1);
        scores.add(90.1);
          scores.add(180.1);
          scores.stream().sorted((o1,o2)->Double.compare(o2,o1)).forEach(System.out::println);
          System.out.println("------------------------------------------------------------------------------");
          scores.stream().sorted().limit(2).forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");
            scores.stream().sorted().skip(2).forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");
            scores.stream().sorted().forEach(System.out::println);
            scores.stream().distinct().sorted().forEach(
                    System.out::println
            );
            scores.stream().forEach(System.out::println);
        System.out.println("--------------------------------------------------------------");
            scores.stream().sorted().map(s->(s+10)).forEach(System.out::println);
        Stream<String> a1=Stream.of("sa","wd");
        Stream<Integer> a2=Stream.of(1,2,3,4,52);
        Stream<Object> a3 = Stream.concat(a1,a2);
        System.out.println(a3.count());
        















    }
}

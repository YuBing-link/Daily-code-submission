package P5_5;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class demo2 {
    public static void main(String[] args) {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher("张三", 18, 10000));
        teacherList.add(new Teacher("李四", 19, 20000));
        teacherList.add(new Teacher("赵六", 21, 40000));
        teacherList.add(new Teacher("王五", 20, 130000));
        teacherList.add(new Teacher("钱七", 22, 50000));
        teacherList.stream().filter(t->t.getSalary()>25000).forEach(System.out::println);
        System.out.println("__________________________________________________________________");
        System.out.println( teacherList.stream().filter(t->t.getSalary()>25000).count());
        long count=teacherList.stream().filter(t ->t.getSalary()>5000).count();
        System.out.println(count);
        System.out.println("__________________________________________________________________");
        System.out.println(teacherList.stream().max((t1,t2)->Double.compare(t1.getSalary(), t2.getSalary())));
        Optional<Teacher> maxTeacherOptional = teacherList.stream().max((t1,t2)->Double.compare(t1.getSalary(),t2.getSalary()));
        System.out.println(maxTeacherOptional);
        Teacher maxTeacher = maxTeacherOptional.get();
        System.out.println(maxTeacher);
        System.out.println(teacherList.stream().min((t1,t2)->Integer.compare(t1.getAge(),t2.getAge())));

        List<String> list = new ArrayList<>();
        list.add("王者荣耀");
        list.add("和平精英");
        list.add("英雄联盟");
        list.add("穿越火线");
        list.add("绝地求生");
        list.add("英雄联盟");
        list.add("穿越火线");
        list.add("绝地求生");
        Stream<String> s1 =list.stream();
        List<String> list1=s1.collect(Collectors.toList());
        System.out.println(list1);
        Set<String> set = list1.stream().collect(Collectors.toSet());
        System.out.println(set);
        Set<String> set2 = new HashSet<>();
        set2.addAll(list1);
        System.out.println(set2);
        System.out.println("___________________________________________________________________________________");
        Stream<Teacher> s4=teacherList.stream();
        Map<String, Double> map = s4.collect(Collectors.toMap(Teacher::getName,Teacher::getSalary));
        System.out.println(map);
        System.out.println(teacherList.stream().collect(Collectors.toMap(t-> t.getName(),t->t.getAge())));
        System.out.println(teacherList.stream().collect(Collectors.toMap(new Function<Teacher, String>() {


                                                                             @Override
                                                                             public String apply(Teacher teacher) {
                                                                                 return teacher.getName();
                                                                             }
                                                                         }, new Function<Teacher, Double>() {
                                                                             @Override
                                                                             public Double apply(Teacher teacher) {
                                                                                 return teacher.getSalary();
                                                                             }
                                                                         }
        )));

    }
}

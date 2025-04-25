package interClass;

import java.util.Arrays;
import java.util.Comparator;

public  class Test {
    public static void main(String[] args) {
        //
        String[] names = {"撒三", "下四", "啊五", "哦六", "篇七"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));



    }

     public static void test() {
        Animal[] animals = new Animal[6];
        animals[0] = new Animal("小猫", "抓老鼠", 10);
        animals[1] = new Animal("小狗", "看家", 15);
        animals[2] = new Animal("小鸡", "鸡 chirp", 12);
        animals[3] = new Animal("小鸭子", "鸭子 quack", 11);
        animals[4] = new Animal("小猪", "猪 neigh", 22);
        animals[5] = new Animal("老虎", "捕猎", 31);
       // Arrays.sort(animals,Animal::Animaltest);
        People b=Animal::new;
        
        Animal a=new Animal();
        Arrays.sort(animals,a::lsap);
        for (int i = 0; i < animals.length; i++) {
            System.out.println(animals[i]);
        }

    }


}
@FunctionalInterface
interface People {
    void people();

}



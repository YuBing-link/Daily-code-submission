package threadPooltest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class executurs {
    public static void main(String[] args) {

        ExecutorService pool=Executors.newFixedThreadPool(3);
        Callable<String> s=new MyCallble();
        Future<String> f1=pool.submit(s);
        Future<String> f2=pool.submit(s);
        Future<String> f3=pool.submit(s);
        try {
            System.out.println(f3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(f1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(f2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package threadPoolExecutor;

import java.util.concurrent.*;

public class ExeturPool {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(3,5,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
//        Runnable r=new Myreunable();

//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
//        pool.execute(r);
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

        //一般不关闭线程池
        pool.shutdown();
    }
}

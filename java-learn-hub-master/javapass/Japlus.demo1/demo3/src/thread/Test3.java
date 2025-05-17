package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test3 {
    public static void main(String[] args) {
        Callable<String> callable1 = new testcallable(100);
        FutureTask<String> f1 = new FutureTask<>(callable1);
        new Thread(f1).start();
        try {
            System.out.println(f1.get());
        }  catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        FutureTask<String> s1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int sum = 0;
                for (int i = 0; i <100 ; i++) {
                    sum+=i;
                }
                return "1dao100="+sum;
            }
        });
        new Thread(s1).start();
        try {
            System.out.println(s1.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
class testcallable implements Callable<String> {
    private int n;
    public testcallable(int n){
        this.n=n;
    }


    public String call() {
        int sun=0;
        for (int i = 1; i <=n ; i++) {
            sun+=i;
        }
        return "1到"+n+"="+sun;
    }
}
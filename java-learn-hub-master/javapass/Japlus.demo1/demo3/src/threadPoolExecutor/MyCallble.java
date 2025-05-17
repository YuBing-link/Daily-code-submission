package threadPoolExecutor;

import java.util.concurrent.Callable;

public class MyCallble implements Callable<String> {

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i <10 ; i++) {
            sum += i;
        }
        return Thread.currentThread().getName()+"输出"+sum;
    }
}

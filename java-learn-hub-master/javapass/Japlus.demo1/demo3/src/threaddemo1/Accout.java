package threaddemo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Accout {
    private String cradId;
    private double money;
    private final Lock lock = new ReentrantLock();
    public  void DrawMoney(double money) {
        String name=Thread.currentThread().getName();
            lock.lock();
        try {
            if(this.money>=money){
                System.out.println(name+ "余额"+this.money);
                this.money -=money;
                System.out.println(name+"取钱成功,余额"+this.money);
            }
            else {
                System.out.println("余额不足");
            }
        } finally {
            lock.unlock();
        }


    }





}

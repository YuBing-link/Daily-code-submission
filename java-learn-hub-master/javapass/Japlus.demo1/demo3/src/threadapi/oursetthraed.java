package threadapi;

public class oursetthraed extends Thread {
    public oursetthraed(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println("子线程"+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

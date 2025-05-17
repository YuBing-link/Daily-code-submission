package threadapi;

public class demoapi {
    public static void main(String[] args) {
        Thread t = new oursetthraed("desss");

//        t.setName("demo");
        t.start();
        System.out.println(t.getName());
        Thread thread = Thread.currentThread();
        thread.setName("master");
        System.out.println(thread.getName());
        for (int i = 0; i <10 ; i++) {
            System.out.println("主线程"+i);
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }


}

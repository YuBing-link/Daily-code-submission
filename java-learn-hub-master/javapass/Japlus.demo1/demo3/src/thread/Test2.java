package thread;

public class Test2 {
    public static void main(String[] args) {
        //new Thread(new testrunable()).start();

//        Runnable r=new testrunable();
//        Thread t=new Thread(r);
//        t.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <10 ; i++) {
//                    System.out.println("zi");
//                }
//            }
//        }).start();
//        new Thread(()->{
//            for (int i = 0; i <10 ; i++) {
//                System.out.println("zi");
//            }
//        }).start();

        new Thread(Test2::run).start();


        for (int i = 0; i <10 ; i++) {
            System.out.println("zhu");
        }

    }

    private static void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("zi");
        }
    }

}
class testrunable implements Runnable{


    @Override
    public  void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println("zi");
        }
    }
}

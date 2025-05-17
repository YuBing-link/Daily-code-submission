package thread;

public class Test {
    public static void main(String[] args) {

        Thread t1 = new tsetthread();
        t1.start();
        for (int i = 0; i <10 ; i++) {
            System.out.println("主");
        }

    }


}
class tsetthread extends Thread {

    @Override
    public void run() {

        for (int i = 0; i <10 ; i++) {
            System.out.println("子");

        }


    }
}



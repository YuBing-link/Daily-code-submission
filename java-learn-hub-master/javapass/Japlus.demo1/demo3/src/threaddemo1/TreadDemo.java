package threaddemo1;

public class TreadDemo {
    public static void main(String[] args) {
        Accout acfc = new Accout("12311",10000);
       new DrawThrea("小红",acfc).start();
        new DrawThrea("小王",acfc).start();

    }


}

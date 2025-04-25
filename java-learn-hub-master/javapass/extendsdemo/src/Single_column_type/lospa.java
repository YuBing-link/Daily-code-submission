package Single_column_type;

public class lospa {
    public static void main(String[] args)  {
        singleA a= singleA.get();
        singleA a1 = singleA.get();

        System.out.println(a);
        System.out.println(a1);
        System.out.println(a==a1);
        singleB b=singleB.getB();
        singleB b1 = singleB.getB();
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b==b1);

    }
}

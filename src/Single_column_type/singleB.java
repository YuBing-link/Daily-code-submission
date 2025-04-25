package Single_column_type;
//懒汉式单例
public class singleB {
    private static  singleB b;
    private singleB(){}
    public static singleB getB(){
        if(b==null){
            b=new singleB();
        }
        return b;
    }

}

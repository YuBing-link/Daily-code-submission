
package Single_column_type;
//饿汉式单例
public class singleA {
    private static final singleA a = new singleA();
    private singleA(){}
    public static singleA get() {
        return a;
    }
}

public class javalearn01 {
  public static void main(String[]arg)
  {

      Scanner w1 = new Scanner(System.in);
      System.out.println("请输入第一个整数：");
      int a = w1.nextInt();
      System.out.println("请输入第二个整数：");
      int b = w1.nextInt();
      System.out.println("两数相加结果："+(a+b));
      System.out.println("两数相减结果："+(a-b));
      System.out.println("两数相乘结果："+(a*b));
      System.out.println("两数相除结果："+(a/b));
      System.out.println("两数相除余数："+(a%b));


  }
}

public class Yrad {
  int h;
  int L;
  public Yrad(int h, int L)
  {
    this.h = h;
    this.L = L;
  }
  public void getAcreage(){
      System.out.println("院子面积：" +(1.0*L*h));
  }
  public void getPerimeter(){
      System.out.println("院子周长：" + (1.0*(L+h)*2));
  }



}

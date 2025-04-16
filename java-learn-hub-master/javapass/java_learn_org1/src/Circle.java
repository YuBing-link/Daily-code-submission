public class Circle {
    int  r;
    int s;
    public Circle(int r)
    {
        this.r=r;
        this.s=(int)(Math.PI*r*r);
    }
    public void show()
    {
        System.out.println("圆的半径为："+r+"，圆的面积为："+s);
    }
}

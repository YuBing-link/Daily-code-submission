package Smart_home_appliance_control_system;
//目的：控制家电
//控制目标：洗衣机，空调，电视，电灯
//控制方式：集成控制软件
//一个父类，为家电模板，四个子类，一个接口作为开关，一个集成系统，一个展示选项板
//。。。。。。
public class Test {
    public static void main(String[] args) {
        home_app[] JD=new home_app[4];
        JD[0]=new Television("小米电视",false);
        JD[1]=new airConditioner("美的空调",true);
        JD[2]=new light("LED节能灯",true);
        JD[3]=new washMachine("尚德洗衣机",false);
        while (true){

           palette c=new  palette(JD);
           new  smartControlSystem(JD);
           if(c.getD()==-2){
               System.out.println("退出app");
           }
        }
    }
}

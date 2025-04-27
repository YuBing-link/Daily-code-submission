package Smart_home_appliance_control_system;

import java.util.Scanner;

//智能控制系统
public class smartControlSystem {


    public smartControlSystem(home_app[] JD) {
       palette s=new palette();
       String p=null;

       Scanner sc = new Scanner(System.in);
       System.out.println("当前"+(JD[s.getD()].getName())+"的状态:"+(JD[s.getD()].isStatus()?"运行中":"关闭中"));
       if(JD[s.getD()].isStatus()){
           System.out.println("是否关闭"+JD[s.getD()].getName());
           p=sc.next();
       }
       else {
           System.out.println("是否开启"+JD[s.getD()].getName());
           p=sc.next();
       }
       if (p.equals("是")) {
           JD[s.getD()].press();
           System.out.println("当前"+(JD[s.getD()].getName())+"的状态:"+(JD[s.getD()].isStatus()?"运行中":"关闭中"));
       }

    }
}

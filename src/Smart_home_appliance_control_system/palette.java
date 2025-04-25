package Smart_home_appliance_control_system;

import java.util.Scanner;

//选项板
public class palette {
    int d=7;
    public palette(){}
    public palette(home_app[] JD){
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < JD.length;i++){
            System.out.println(JD[i].getName()+"  "+(i+1));
        }
        System.out.println("退出app请输入：-1");
        Scanner in = new Scanner(System.in);
        System.out.print("请输入需要控制的家电序号:");
        d=in.nextInt()-1;
        System.out.println();
        System.out.println("--------------------------------------------------");
    }
    public int getD(){
        return d;
    }


}

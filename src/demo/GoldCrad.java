package demo;

public class GoldCrad extends Card{
    @Override
    public void withdraw(double memony) {
        super.withdraw(memony*0.8);
        if(checkBalance()>=200){
            System.out.println("获得洗车卷");
        }
    }


}

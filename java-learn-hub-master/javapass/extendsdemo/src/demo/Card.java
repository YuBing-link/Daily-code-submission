package demo;

public abstract class Card {
    private String name;
    private int cardId;
    public static double bablance;
    private double memony;
    public Card() {}
    public static String  kilxw(){
        if(bablance>=5000){
            return "Gold";
        }
        else if(bablance>=2000){
            return "Silver";
        }
        return null;
    }
    public static Card wp(){
        String Gold="Gold";
        String Silver="Silver";
        Card w=null;
        if(Gold.equals(kilxw())){
            w=new GoldCrad();
        }
        else if (Silver.equals(kilxw())){
            w=new SilverCrad();
        }
        return w;
    }

    public void deposit(double memony){
        bablance+=memony;
    }
    public void withdraw(double memony){
        if(bablance<memony){
            System.out.println("余额不足");
            return;
        }
        bablance-=memony;

    }
    public double checkBalance(){
        return bablance;
    }


}

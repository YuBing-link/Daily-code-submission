package demo;

public class card extends deposit {
    private static int x=0;

    public String Assess(){
        x+=getMemony();
        x-=getMemonyPrice();
        if(x>=5000){
            return "金";
        }
        else if(x>=2000) {
            return "银";
        }
        else {
            return "普";
        }
    }


}

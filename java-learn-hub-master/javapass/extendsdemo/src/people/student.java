package people;

public class student extends people {
    private int sorce;

    public student(){
    }
    public student(String name,int age,char sex,int sorce){
       super(name,age,sex);
       this.sorce=sorce;
    }



    @Override
    public String toString() {
        return "{" +super.toString()+
                " " + sorce +
                '}';
    }
}

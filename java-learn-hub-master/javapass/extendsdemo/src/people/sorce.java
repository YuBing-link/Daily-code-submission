package people;

public class sorce extends student {
    private int gui;
    public sorce(){}
    public sorce(String name) {
        this(name,0,'p',65,890);
    }
    public sorce(String name ,int age,char sex,int s,int gui) {
        super(name, age, sex, s);
        this.gui = gui;
    }


    public String toString() {
        return super.toString()+" "+gui;
    }

}

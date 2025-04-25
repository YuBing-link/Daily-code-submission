package demo;

public class SilverCrad extends Card{

    @Override
    public void deposit(double memony) {
        super.withdraw(memony*0.9);
    }

}

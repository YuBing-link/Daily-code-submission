package demo;

public class deposit extends withdraw{
    private int memony;
    public deposit() {}
    public deposit(int memony){
        this.memony = memony;
    }

    public int getMemony() {
        return memony;
    }

    public void setMemony(int memony) {
        this.memony = memony;
    }
}

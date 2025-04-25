package people;

public class people {
    private String name;
    public int age;
    protected  char sex;
    public people() {}
    public people(String name, int age,char sex) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    protected void posName(){
        System.out.println("666666666");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name+" "+age+" "+sex;
    }




    public void setAge(int age) {
        this.age = age;
    }
}

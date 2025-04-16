public class student {
    int id;
    int age;
    int grade;
    String sex;
    public student(){

    }
    public void study(){
        System.out.println("学号为"+id+"的学生正在学习");
    }
    public void examination(){
        System.out.println(grade+"年级正在考试");
    }
    public void tell(){
        System.out.print("正在讲话的是一个"+age+"岁的学生");
    }


    public int setAge(int age){
        this.age=age;
        if(age<0||age>120){
            this.age=18;
        }
        return age;
    }
    public void getAge(){

        System.out.println("年龄为"+age);
    }
    public String setSex(String sex){

        if(sex.equals("男")||sex.equals("女")){
            return this.sex=sex;
        }
        else{
            return this.sex="保密";
        }
    }
    public void getSex(){
        System.out.println("性别为"+sex);
    }




    }

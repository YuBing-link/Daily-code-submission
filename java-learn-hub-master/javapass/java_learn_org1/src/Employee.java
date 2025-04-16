public class Employee {
    String name;
    int age;
    String dept;
    String company;
    public Employee(String name, int age, String dept, String company)
    {
        this.name = name;
        this.age = age;
        this.dept = dept;
        this.company = company;
    }
    public void showInfos()
    {
        System.out.println("姓名:" + name + "年龄:" + age + "部门:" + dept + "公司:" + company);
    }
    public static int compareByAge(int age1, int age2)
    {
        return Math.max(age1, age2);
    }
}

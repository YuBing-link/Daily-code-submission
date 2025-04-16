public class PoL_4_12 {


    public static void main(String[] arg) {
        Employee e1 = new Employee( "张三", 25, "技术部", "华为");
        Employee e2 = new Employee( "李四", 26, "技术部", "阿里");
        e1.showInfos();
        e2.showInfos();
        System.out.println(Employee.compareByAge(e1.age, e2.age));
    }



}

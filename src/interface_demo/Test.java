package interface_demo;

public class Test {
    public static void main(String[] args) {
        Student[] student = new Student[15];
        //生成15个学生信息
        for (int i = 0; i < student.length; i++) {
            student[i] = new Student("学生" + i, (int) (Math.random() * 100), (int) (Math.random() * 100));
        }
        planA a=new planA(student);
        planB b=new planB(student);
        a.allStudents();a.averageStudentscore();
        b.allStudents();b.averageStudentscore();

    }

}

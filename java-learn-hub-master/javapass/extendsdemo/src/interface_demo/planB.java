package interface_demo;

public class planB implements studentinterface{
    private Student[] student;
    public planB(Student[] student) {
        this.student = student;
    }
    @Override
    public void allStudents() {
        System.out.println("__________________planB_________________________");

        for (int i = 0; i < student.length; i++) {
            System.out.println(student[i]);
        }
    }

    @Override
    public void averageStudentscore() {
        double sum = 0;
        for (int i = 0; i < student.length; i++) {
            sum += student[i].getScore();
        }
        System.out.println("平均成绩："+sum/student.length);
        System.out.println("__________________planB_________________________");

    }

}

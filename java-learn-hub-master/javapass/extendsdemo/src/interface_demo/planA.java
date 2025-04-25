package interface_demo;

public class planA implements studentinterface {
    private Student[] student;

    public planA(Student[] student) {
        this.student = student;
    }

    @Override
    public void allStudents() {
        System.out.println("__________________planA_________________________");
        for (int i = 0; i < student.length; i++) {
            System.out.println(student[i]);
        }
    }

    @Override
    public void averageStudentscore() {
        double max = student[0].getScore();
        double min = student[0].getScore();
        double sum = 0.0;
        for (int i = 0; i < student.length; i++) {
            if (max < student[i].getScore()) {
                max = student[i].getScore();
            }
            if (min > student[i].getScore()) {
                min = student[i].getScore();
            }
            sum += student[i].getScore();
        }
        System.out.println("最大值："+max);
        System.out.println("最小值："+min);
        System.out.println("平均值："+(sum-max-min)/(student.length-2));
        System.out.println("__________________planA_________________________");

    }
}
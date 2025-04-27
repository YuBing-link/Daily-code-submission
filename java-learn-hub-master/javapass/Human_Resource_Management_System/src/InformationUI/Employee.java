package InformationUI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//"ID", "姓名","性别","电话", "职位", "薪水","部门"
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String ID;
    private String name;
    private String sex;
    private String phone;
    private String position;
    private double salary;
    private String department;

}

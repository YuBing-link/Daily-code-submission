package InformationUI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户名，登录密码
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uesr {
    public String name;
    public char[] password;
}

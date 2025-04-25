package interClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Animal {
    //动物姓名 爱好 最长寿命
    private String name;
    private String hobby;
    private int life;





    public static int Animaltest(Animal s1, Animal s2 ){
        return s1.getLife()-s2.getLife();
    }

    public  int lsap(Animal s1, Animal s2){
        return s1.getLife()-s2.getLife();

    }




}

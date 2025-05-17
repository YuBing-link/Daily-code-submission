package P5_2.set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People implements Comparable<People>{
    private String name;
    private int age;
    private double price;

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        People people = (People) o;
//        return age == people.age ;//&& Double.compare(price, people.price) == 0 && Objects.equals(name, people.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(age);
//    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}'+'\n';
    }
    public int compareTo(People people) {
        return this.age - people.age;
    }
}

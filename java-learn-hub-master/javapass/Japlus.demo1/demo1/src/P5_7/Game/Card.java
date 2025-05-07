package P5_7.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String size;
    private String color;
    private int num;

    @Override
    public String toString() {
        return color+size;
    }
}

package P4_27.Generics;

import java.util.ArrayList;

public class  div<E> {
    ArrayList div = new ArrayList();
    public void add(E a){
        div.add(a);
    }
    public void remove(E ai) {
        div.remove(ai);
    }
    public String toString() {
       return div.toString();
    }

}

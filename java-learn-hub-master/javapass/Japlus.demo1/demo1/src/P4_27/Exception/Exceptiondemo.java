package P4_27.Exception;

public class Exceptiondemo {
    public static void main(String[] args) {
        try {
            div(1,2);
        }catch(myException e){
           e.printStackTrace();
        }

    }
    public  static int div(int a, int b) throws myException {
        if(b==0){
            throw new myException();
        }

        return a/b;
    }


}


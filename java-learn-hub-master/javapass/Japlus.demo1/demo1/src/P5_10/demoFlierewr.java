package P5_10;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class demoFlierewr {
    public static void main(String[] args) {
        try(
              //  Reader reader = new FileReader("demo1/src/P5_9/cof.txt");
                Writer writer = new FileWriter("demo1/src/P5_9/cof.txt",true);
        ){
//            char[] data = new char[1024];
//            int len = 0;
//            while ((len = reader.read(data))!=-1){
//           String str=new String(data,0,len);
//                System.out.println(str);
//
//            }
            writer.write("\r\n");
        writer.write("wangzhery");
        writer.write("java",1,2);
        writer.write("wsasxasc".toCharArray());
        writer.flush();
        writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

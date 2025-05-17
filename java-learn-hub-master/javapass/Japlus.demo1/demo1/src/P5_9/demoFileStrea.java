package P5_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class demoFileStrea {
    public static void main(String[] args) throws Exception {
//        InputStream inputStream =  new FileInputStream("D:\\代码文件库\\45s.txt");
//        int b;
//        byte[] data = new byte[3];
//        while ((b=inputStream.read(data))!=-1)
//        {
////            String str = new String(data,0,b);
////            System.out.println(str);
//        }
//        InputStream is = new FileInputStream("D:\\代码文件库\\c循环倒向.txt");
//        byte[] data1 = is.readAllBytes();
//        String str1 = new String(data1);
////        System.out.print(str1);
////        System.out.print(new  String(data1));
//        is.close();
//        inputStream.close();
        OutputStream os=new FileOutputStream("D:\\代码文件库\\c14.txt",true);
        os.write(97);
        os.write('a');
            System.out.println(97);
//        byte[] data2 = "桀".getBytes();
//        os.write(data2);
//        os.write("\r\n".getBytes());
//
//        byte[] data3 = "scn哪位大神".getBytes();
//        os.write(data3);
//        os.write("\r\n".getBytes());
    }

}

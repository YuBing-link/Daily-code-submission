package P5_10;

import java.io.*;

public class demoFile {
    public static void main(String[] args)  {
       InputStream fis = null;
       OutputStream fos = null;
        try {
            fis = new FileInputStream("C:\\Users\\aex\\Pictures\\13390903166352494.jpeg");
            fos = new FileOutputStream("D:\\pixiv\\spsas.jpeg");

           fos.write(fis.readAllBytes());
       }
       catch (Exception e){
           e.printStackTrace();
       }finally {
            try {
                if (fos != null && fos != null) {
                    fis.close();
                    fos.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            }

    }
//public static void main(String[] args) {
//    // 使用 try-with-resources 自动关闭资源
//    try (InputStream fis = new FileInputStream("C:\\Users\\aex\\Pictures\\13390903166352494.jpeg");
//         OutputStream fos = new FileOutputStream("D:\\pixiv\\output.jpg")) { // 添加文件名
//
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len = fis.read(buffer)) != -1) { // 修正read方法
//            fos.write(buffer, 0, len);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
}

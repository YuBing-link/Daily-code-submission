package P5_7.flieD;

import java.io.File;
import java.util.Arrays;

public class fileDemo {
    public static void main (String[] args) throws Exception {
        File f1 = new File("D:\\代码文件库\\算法\\有效字母异位词.c");
        System.out.println(f1.length());
        System.out.println(f1.getName());
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        File f2 = new File("demo1\\src\\P5_5\\Teacher.java");
        System.out.println(f2.length());
        File f3 = new File("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo1\\src\\P5_5\\sixmancsaa.txt");
        System.out.println(f3.exists());
        System.out.println(f3.createNewFile());
        File f4 = new File("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo1\\src\\P5_5\\www\\aaa");
        System.out.println(f4.mkdirs());
        System.out.println(f4.delete());
        System.out.println(f3.delete());
        File f5 = new File("D:\\代码文件库\\算法");
        String[] names = f5.list();
        for (String s:names){
            System.out.println(s);
        }
        File f6=new File("D:\\代码文件库\\算法");
        File[] files=f6.listFiles();
        for (File f : files){
            System.out.println(f.getAbsoluteFile());
        }
        File dir1=new File("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo1\\src\\P5_5\\www\\lll");
        File[] files1=dir1.listFiles();
        System.out.println(Arrays.toString(files1));

    }

}

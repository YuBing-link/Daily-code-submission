package P5_11;

import java.io.*;

public class demoInputStream {
    public static void main(String[] args) {
        try (
//                BufferedReader in = new BufferedReader(  new FileReader("demo1/src/P5_11/kls.txt"));
//                InputStream in=new FileInputStream("demo1/src/P5_11/kls.txt");
//                InputStreamReader is =new InputStreamReader(in,"GBK");
//                BufferedReader b = new BufferedReader(is);
                BufferedReader b=new BufferedReader(new InputStreamReader(new FileInputStream("demo1/src/P5_11/kls.txt"),"GBK"));

                ){
            while(b.readLine()!=null) {
                System.out.println(b.readLine());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try(
                PrintWriter out = new PrintWriter(new FileWriter("demo1/src/P5_11/kls.txt",true));
                ){
            out.println(97);
            out.println(99.9);
            out.println(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("demo1/src/P5_11/kls.txt"));
                DataInputStream dis = new DataInputStream(new FileInputStream("demo1/src/P5_11/kls.txt"));
        ) {
            dos.writeInt(34);
            dos.writeBoolean(false);
            dos.writeUTF("wusca");
            System.out.println(dis.readInt());
//            System.out.println(dis.read());
//            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}

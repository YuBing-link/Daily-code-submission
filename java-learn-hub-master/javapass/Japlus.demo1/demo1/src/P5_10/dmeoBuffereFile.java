package P5_10;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class dmeoBuffereFile {
    public static void main(String[] args) {
        try (
                BufferedReader in = new BufferedReader(new FileReader("demo1/src/P5_10/lsp.txt"));

                BufferedWriter bw = new BufferedWriter( new FileWriter("demo1/src/P5_10/lsp.txt",true));){
            List<String> ws = new ArrayList<String>();
            int len=0;
            String line;
            while((line=in.readLine())!=null){
            if (!line.isEmpty())
                ws.add(line);
            }
            try (FileWriter fileWriter = new FileWriter("demo1/src/P5_10/lsp.txt")) {
            } catch (Exception e) {
                e.printStackTrace();
            }
            Collections.sort(ws);
            for (String wallet : ws){
                bw.write(wallet);
                bw.newLine();
                bw.newLine();

            }

            } catch (Exception e) {
                e.printStackTrace();
            }






    }
}

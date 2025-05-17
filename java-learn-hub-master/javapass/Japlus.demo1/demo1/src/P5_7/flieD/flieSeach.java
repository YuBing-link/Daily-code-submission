package P5_7.flieD;

import java.io.File;
import java.io.IOException;

public class flieSeach {
    public static void main(String[] args) {
        File file = new File("D:\\");
        try {
            flieSeach(file,"WeChat.exe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param dir 文件
     * @param name 名称
     */
    public static void flieSeach(File dir,String name) throws IOException {
        if(dir==null|| !dir.exists()|| dir.isFile()){
            return;
        }
        File[] outFile = dir.listFiles();
        if(outFile!=null&&outFile.length >0){
            for(File f:outFile){
                if(f.isFile()){
                    if(f.getName().contains(name)){
                        System.out.println(f.getAbsoluteFile());
                        Runtime.getRuntime().exec(f.getAbsolutePath());
                        return;
                    }
                }
                else {
                    flieSeach(f,name);
                }



            }
        }


    }




}

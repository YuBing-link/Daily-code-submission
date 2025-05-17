package P5_11;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class demoCommins {
    public static void main(String[] args) throws Exception {
        FileUtils.copyFile(new File("demo1/src/P5_10/lsp.txt"),new File("demo1/src/P5_11/lsp.txt"));
    }
}

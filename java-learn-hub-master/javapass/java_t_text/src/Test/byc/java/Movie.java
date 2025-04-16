package Test.byc.java;

public class Movie {
    public static void main(String[] args) {
               Compose []Movie=new Compose[5];
               Movie[0]=new Compose(1,"《大话西游》","武帝");
               Movie[1]=new Compose(2,"《西游记》","孙悟空");
               Movie[2]=new Compose(3,"《西游记》","唐僧");
               Movie[3]=new Compose(4,"《西游记》","猪八戒");
               Movie[4]=new Compose(5,"《西游记》","唐僧");
               Display.getInstance(Movie);
    }
}

package Test.byc.java;

public class Display {
    public static void getInstance(Compose []movie){
        for (int i = 0; i < movie.length; i++) {
            System.out.println(movie[i].getMovieId()+" "+movie[i].getMovieName()+" "+movie[i].getActorName());

        }
    }

}

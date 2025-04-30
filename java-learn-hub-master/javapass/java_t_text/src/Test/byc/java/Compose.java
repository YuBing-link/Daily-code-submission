package Test.byc.java;

public class Compose {
    private String movieName;
    private String actorName;
    private int movieId;
    public Compose(int movieId,String movieName,String actorName){
        this.movieId = movieId;
        this.movieName = movieName;
        this.actorName = actorName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public String getMovieName() {
        return movieName;
    }
    public String getActorName() {
        return actorName;
    }
    public int getMovieId() {
        return movieId;
    }
}

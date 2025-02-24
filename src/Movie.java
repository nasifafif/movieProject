public class Movie {
    public String title;
    public String[] cast;
    public String director;
    public String overview;
    public int runtime;
    public double userRating;

    public Movie(String title, String[]cast, String director, String overview, int runtime, double userRating){
        this.title=title;
        this.cast=cast;
        this.director=director;
        this.overview=overview;
        this.runtime=runtime;
        this.userRating=userRating;
    }

    public String getTitle(){
        return title;
    }
    public String[] getCast(){
        return cast;
    }
    public String getDirector(){
        return director;
    }
    public String getOverview(){
        return overview;
    }
    public int getRuntime(){
        return runtime;
    }
    public double getUserRating(){
        return userRating;
    }

}

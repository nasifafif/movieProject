import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MovieCollection
{
    private int movieNum;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    public MovieCollection(){}

    public void importData(){
        try{
            File myFile = new File("src\\movies_data.csv");
            Scanner scanner = new Scanner(myFile);
            while(scanner.hasNext()){
                String data = scanner.nextLine();
                String [] split = data.split(",");
                String title = split[0];
                String[] cast = split[1].split("\\|");
                String director = split[2];
                String overview = split[3];
                int runtime = Integer.parseInt(split[4]);
                double userRating = Double.parseDouble(split[5]);
                Movie movie = new Movie(title,cast,director,overview,runtime,userRating);
                movies.add(movie);

            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void menu(){
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        Scanner scanner = new Scanner(System.in);

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }
    public boolean linearSearchFound(ArrayList<Movie> title, String titleName){
        for(int i = 0; i<title.size(); i++){
            if(movies.get(i).getTitle().toLowerCase().equals(titleName.toLowerCase())){
                movieNum = i;
                return true;
            }
        }
        return false;
    }


    public void searchTitles(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome! Enter a title of a movie to search the database.");
        String title = scan.nextLine();
        if(linearSearchFound(movies,title)){
            System.out.println("Title: " + movies.get(movieNum).getTitle());
            System.out.println("Cast: " + Arrays.toString(movies.get(movieNum).getCast()));
            System.out.println("Director: " + movies.get(movieNum).getDirector());
            System.out.println("Overview: " + movies.get(movieNum).getOverview());
            System.out.println("Runtime: " + movies.get(movieNum).getRuntime());
            System.out.println("User rating: " + movies.get(movieNum).getUserRating());
        }
        else{
            System.out.println("Movie not found.");
        }

    }
    public void searchCast(){};


}

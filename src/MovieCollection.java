import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MovieCollection
{
    private int movieNum;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<String> possibleMovies = new ArrayList<>();
    private ArrayList<Integer> listOfIndexes = new ArrayList<>();
    public MovieCollection(){}

    public void importData(){
        try{
            File myFile = new File("src\\movies_data.csv");
            Scanner scanner = new Scanner(myFile);
            while(scanner.hasNext()){
                String data = scanner.nextLine();
                String[] split = data.split(",");
                String title = split[0];
                String[] cast = split[1].split("\\|");
                String director = split[2];
                String overview = split[3];

                int runtime = 0;
                try {
                    runtime = Integer.parseInt(split[4]);
                } catch (NumberFormatException e) {
                    System.out.print("");
                }

                double userRating = 0.0;
                try {
                    userRating = Double.parseDouble(split[5]);
                } catch (NumberFormatException e) {
                    System.out.println();
                }

                Movie movie = new Movie(title, cast, director, overview, runtime, userRating);
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
    public void linearSearchMovie(ArrayList<Movie> title, String titleName){

        for(int i = 0; i<title.size(); i++){
            if(movies.get(i).getTitle().toLowerCase().contains(titleName.toLowerCase())){
                movieNum = i;
                listOfIndexes.add(movieNum);
                possibleMovies.add(movies.get(movieNum).getTitle());
            }
        }
    }


    public void searchTitles(){
        int count = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome! Enter a title of a movie to search the database.");
        String title = scan.nextLine();
        linearSearchMovie(movies,title);
        if(!possibleMovies.isEmpty()){
            for(int i = 0; i<possibleMovies.size(); i++){
                count++;
                System.out.println(count + ":" + possibleMovies.get(i));
            }
            System.out.println("Which movie would you like to learn about? (Enter number)");
            int choice = scan.nextInt()-1;
            choice = listOfIndexes.get(choice);
            System.out.println("Title: " + movies.get(choice).getTitle());
            System.out.println("Cast: " + Arrays.toString(movies.get(choice).getCast()));
            System.out.println("Director: " + movies.get(choice).getDirector());
            System.out.println("Overview: " + movies.get(choice).getOverview());
            System.out.println("Runtime: " + movies.get(choice).getRuntime() + " minutes");
            System.out.println("User rating: " + movies.get(choice).getUserRating());
        }
        else{
            System.out.println("Movie not found.");
        }
    }

    public void searchCast(){


    }


}

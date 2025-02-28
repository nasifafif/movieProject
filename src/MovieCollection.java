import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieCollection
{
    private int movieNum;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<String> possibleMovies = new ArrayList<>();
    private ArrayList<String> possibleCast = new ArrayList<>();
    private ArrayList<Integer> idxs = new ArrayList<>();
    public MovieCollection(){}


    private void printInfo(){
        System.out.println("Title: " + movies.get(movieNum).getTitle());
        System.out.println("Cast: " + Arrays.toString(movies.get(movieNum).getCast()));
        System.out.println("Director: " + movies.get(movieNum).getDirector());
        System.out.println("Overview: " + movies.get(movieNum).getOverview());
        System.out.println("Runtime: " + movies.get(movieNum).getRuntime() + " minutes");
        System.out.println("User rating: " + movies.get(movieNum).getUserRating());
    }

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
    public void start(){
        importData();
        menu();
    }
    public void linearSearchMovie(String titleName){

        for(int i = 0; i<movies.size(); i++){
            if(movies.get(i).getTitle().toLowerCase().contains(titleName.toLowerCase())){
                movieNum = i;
                possibleMovies.add(movies.get(movieNum).getTitle());
            }
        }
    }

    public void linearSearchCast(String actor) {
        for (int i = 0; i < movies.size(); i++) {
            String[] cast = movies.get(i).getCast();

            for (String actorInCast : cast) {
                movieNum++;
                if (actorInCast.toLowerCase().contains(actor.toLowerCase())) {
                    possibleCast.add(actorInCast);
                    idxs.add(movieNum);
                }
            }
        }
    }
    public void linearSearchActor(String actor){
        for(int i = 0; i<movies.size(); i++){
            if(Arrays.toString(movies.get(i).getCast()).toLowerCase().contains(actor.toLowerCase())){
                possibleMovies.add(movies.get(i).getTitle());
            }
        }
    }



    public void removeDuplicates() {
        for (int i = 0; i < possibleCast.size(); i++) {
            for (int j = i + 1; j < possibleCast.size(); j++) {
                if (possibleCast.get(i).equals(possibleCast.get(j))) {
                    possibleCast.remove(j);
                    j--;
                }
            }
        }
    }


    public static void sortAlphabetically(List<String> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }



    public void searchTitles(){
        int count = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome! Enter a title of a movie to search the database.");
        String title = scan.nextLine();
        linearSearchMovie(title);
        if(!possibleMovies.isEmpty()){
            sortAlphabetically(possibleMovies);
            for(int i = 0; i<possibleMovies.size(); i++){
                count++;
                System.out.println(count + ":" + possibleMovies.get(i));
            }
            System.out.println("Which movie would you like to learn about? (Enter title exactly)") ;
            String choice = scan.nextLine();
            linearSearchMovie(choice);
            printInfo();

        }
        else{
            System.out.println("Movie not found.");
        }
    }

    public void searchCast(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an actor to see their information.");
        String actor = scan.nextLine();
        linearSearchCast(actor);
        removeDuplicates();
        sortAlphabetically(possibleCast);
        int count = 0;
        for(int i = 0; i<possibleCast.size(); i++){
            count++;
            System.out.println(count + ":" + possibleCast.get(i));
        }
        System.out.println("Which actor's movies would you like to view? (Enter name exactly)");
        actor = scan.nextLine();
        linearSearchActor(actor);
        if(!possibleMovies.isEmpty()){
            sortAlphabetically(possibleMovies);
            for(int i = 0; i<possibleMovies.size(); i++){
                count++;
                System.out.println(count + ":" + possibleMovies.get(i));
            }
            System.out.println("Which movie would you like to learn about? (Enter title exactly)") ;
            String choice = scan.nextLine();
            linearSearchMovie(choice);
            printInfo();

        }






    }


}

import java.util.*;

public class main {
    static String[] Location = {"NEW ORLEANS", "MIAMI", "LOS ANGELES", "LONDON", "VIENNA", "IBIZA", "TOKYO"};
    public static void main(String[] args) {
        System.out.println("press the enter key to start");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Performer[] troupe = new Performer[10];
        boolean Menu = true;

        troupe[0] = new Performer("Johan", "FrenchHorn");
        troupe[1] = new Performer("Zarar", "Fluba");
        troupe[2] = new Performer("Hrehaan", "Guitar");
        troupe[3] = new Performer("Karam", "Drums");
        troupe[4] = new Performer("Bernardo", "Trumpet");

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < troupe.length; i++) {
                if (troupe[i] == null) {
                    continue;
                }
                troupe[i].perform(Location[rand.nextInt(6)]);
            }
        }
        int Input = 0;
        while (Menu) {
            sc.nextLine();
            System.out.println("_________________");
            System.out.println("MAIN MENU");
            System.out.println("_________________");
            System.out.println("1. Print Performers and their Information");
            System.out.println("2. Add a new Performers");
            System.out.println("3. Perform");
            System.out.println("4. Print the Best Performance");
            System.out.println("5. Sort the Performers");
            System.out.println("6. List the performances By Venue");
            System.out.println("7. Find the Venue with the best performance");
            System.out.println("8. QUIT");
            System.out.print("_________________ ");
            try {
                Input = sc.nextInt();
                System.out.println();
                switch (Input) {
                    case 1 -> PrintPerf(troupe);
                    case 2 -> addElm(troupe);
                    case 3 -> NewPerformance(troupe);
                    case 4 -> BestPerf(troupe);
                    case 5 -> Rank(troupe);
                    case 6 -> VenueList(troupe);
                    /////NOT WORKING
                    // case 7 -> findBestVenue(Ensemble);
                    case 8 -> Menu = false;
                    default -> System.out.println("Your answer was not valid please try again");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }

        }

    }
    public static void PrintPerf(Performer[] arr){
        System.out.println();
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                continue;
            }
            System.out.print((i+1)+". ");
            arr[i].getPerformerInfo();
        }
        System.out.println();
        System.out.println();
    }

    public static void addElm(Performer[] arr){
        Scanner sc = new Scanner(System.in);
        int Add =0;
        String NEWName;
        String Instrument;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null){
                Add++;
            }
        }
        System.out.println();
        System.out.print("Please enter the name of new Musician: ");
        NEWName = sc.nextLine();
        System.out.print("Enter name of new Musician's Instrument: ");
        Instrument = sc.nextLine();
        arr[Add] = new Performer(NEWName, Instrument);
    }

    public static void NewPerformance(Performer[] arr){
        PrintPerf(arr);

        Scanner sc = new Scanner(System.in);
        boolean running = false;
        String userInp = "";
        int userInpLocal = 0;
        while (!running) {
            System.out.print("Performance Location: ");
            userInp = sc.nextLine();
            System.out.print("Performer number: ");
            userInpLocal = sc.nextInt();
            if (arr[userInpLocal-1] != null) {
                running = true;
            }
        }
        arr[userInpLocal-1].perform(userInp);
        System.out.println(arr[userInpLocal-1].getNAME()+", "+arr[userInpLocal-1].getPrevLocationLOCATION()+" ("+arr[userInpLocal-1].getPrevRATING()+") ");
        PrintPerf(arr);
    }

    public static void BestPerf(Performer[] arr){
        for (int i = 0; i < arr.length-1; i++)
        {
            if (arr[i] == null){
                continue;
            }
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] == null){
                    continue;
                }
                if (arr[j].getBestRatings() > arr[index].getBestRatings()) {
                    index = j;
                }
            }
            Performer temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Best Performer: "+arr[0].getNAME()+", "+arr[0].getINSTRUMENT()+" ("+arr[0].getBestRatings()+", "+arr[0].getLocationsBest()+") ");
    }

    public static void Rank(Performer[] arr){
        for (int i = 0; i < arr.length-1; i++)
        {
            if (arr[i] == null){
                continue;
            }
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] == null){
                    continue;
                }
                if (arr[j].getAverage() > arr[index].getAverage()) {
                    index = j;
                }
            }
            Performer temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Best Performers: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                continue;
            }
            System.out.print(i+1+"/ ");
            arr[i].getPerformerInfo();
        }
    }

    public static void VenueList(Performer[] arr){
        Scanner sc = new Scanner(System.in);
        int n =0;
        System.out.print("Venue: ");
        String userInp = sc.nextLine();
        userInp = userInp.toUpperCase(Locale.ROOT);
        System.out.println("Performers are: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                continue;
            }
            for (int j = 0; j < arr[i].getLocations().length; j++) {
                if (arr[i].getLocationsElement(j) == null){
                    continue;
                }
                if (arr[i].getLocationsElement(j).equals(userInp)){
                    System.out.println("/ "+arr[i].getNAME()+" "+arr[i].getRatingsElements(j));
                    n++;
                }
            }
        }
        if (n == 0){
            System.out.println("There is no one that has performed her!!!!!!!!!!");
        }
    }

}
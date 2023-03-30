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
        troupe[1] = new Performer("Augustine", "Fluba");
        troupe[2] = new Performer("Ava", "Guitar");
        troupe[3] = new Performer("JJ", "Drums");
        troupe[4] = new Performer("Bernardo", "Trumpet");

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < troupe.length; i++) {
                if (troupe[i] == null) {
                    continue;
                }
                troupe[i].perform(Location[rand.nextInt(6)]);
            }
        }
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
            System.out.println("7. QUIT");
            System.out.print("_________________ ");
            String Input = sc.nextLine();
            try {
                System.out.println();
                if (Input.equals("1")) {
                    PrintPerf(troupe);
                } else if (Input.equals("2")) {
                    addElment(troupe);
                } else if (Input.equals("3")) {
                    NewPerformance(troupe);
                } else if (Input.equals("4")) {
                    BestPerf(troupe);
                } else if (Input.equals("5")) {
                    Rank(troupe);
                } else if (Input.equals("6")) {
                    VenueList(troupe);
                } else if (Input.equals("7")) {
                    break;
                } else {
                    System.out.println("Your answer was not valid please try again");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Try again!");
                continue;
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

    public static void addElment(Performer[] arr){
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
        int userInpLocal = 0;
        boolean running = false;
        String userInp = "";
        while (!running) {
            System.out.print("Please enter the Performance Location: ");
            userInp = sc.nextLine();
            System.out.print("Please enter the Performer number: ");
            userInpLocal = sc.nextInt();
            if (arr[userInpLocal-1] != null) {
                running = true;
            }
        }
        arr[userInpLocal-1].perform(userInp);
        double roundedRating = Math.round(arr[userInpLocal-1].getPrevRATING() * 100.0) / 100.0;
        System.out.println(arr[userInpLocal-1].getNAME()+", "+arr[userInpLocal-1].getPrevLocationLOCATION()+" (rating: "+roundedRating+")");
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
            Performer temperoray = arr[index];
            arr[index] = arr[i];
            arr[i] = temperoray;
        }
        System.out.println("The Best Performer is: "+arr[0].getNAME()+", "+arr[0].getINSTRUMENT()+" ("+arr[0].getBestRatings()+", "+arr[0].getLocationsBest()+") ");
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
        System.out.println("The Best Performers are: ");
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
        int numberOfPerformers = 0;
        System.out.print("Enter the Veneu: ");
        String venue = sc.nextLine();
        venue = venue.toUpperCase(Locale.ROOT);
        System.out.println("The Performers at this venue are: ");
        for (Performer performer : arr) {
            if (performer == null) {
                continue;
            }

            for (int j = 0; j < performer.getLocations().length; j++) {
                if (performer.getLocationsElement(j) == null) {
                    continue;
                }

                if (performer.getLocationsElement(j).equals(venue)) {
                    System.out.println(" / " + performer.getNAME() + " " + performer.getRatingsElements(j));
                    numberOfPerformers++;
                }
            }
        }

        if (numberOfPerformers == 0) {
            System.out.println("No one has performed here, John stop trying to break it bro!");
        }
    }

}

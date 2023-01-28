import java.text.DecimalFormat;
import java.util.*;

public class Performer {
    DecimalFormat df = new DecimalFormat("##.#");

    private final String NAME;
    private final String INSTRUMENT;

    private String PrevLocation;
    private double PrevRATING;

    private final double[] ratings = new double[1000];
    private final String[] locations = new String[1000];

    private double average;
    private int localBest;

    public Performer(String _name, String _instrument){
        NAME = _name;
        INSTRUMENT = _instrument;
    }
    public String getPrevLocationLOCATION() {
        return PrevLocation;
    }
    public double getPrevRATING() {
        return PrevRATING;
    }
    public void getPerformerInfo(){

        System.out.println(NAME+", "+INSTRUMENT+", ("+average+") || Last performance was at "+PrevLocation);
    }
    /*public double[] getRatings(){
        return ratings;
    }*/
    public String getLocationsBest() {
        return locations[localBest];
    }
    public String getLocationsElement(int ele) {
        return locations[ele];
    }
    public String[] getLocations() {
        return locations;
    }
    public double getBestRatings(){
        for (int i = 0; i < ratings.length-1; i++)
        {
            if (ratings[i] == 0){
                continue;
            }
            int min_idx = i;
            for (int j = i+1; j < ratings.length; j++) {
                if (ratings[j] == 0){
                    continue;
                }
                if (ratings[j] > ratings[min_idx]) {
                    min_idx = j;
                    localBest=j;
                }
            }
            double temp = ratings[min_idx];
            ratings[min_idx] = ratings[i];
            ratings[i] = temp;
        }
        return ratings[0];
    }
    public double getRatingsElements(int index){
        return ratings[index];
    }
    public String getNAME() {
        return NAME;
    }
    public String getINSTRUMENT() {
        return INSTRUMENT;
    }
    public void perform(String locationOfPerformance) {
        Random rand = new Random();
        PrevRATING = Double.parseDouble(df.format(rand.nextDouble()*10));
        if (PrevRATING > 10){
            PrevRATING = 10.0;
        }
        PrevLocation = locationOfPerformance;
        int n =0;
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] != 0){
                n++;
            }
        }
        ratings[n] = PrevRATING;
        n=0;
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] != null){
                n++;
            }
        }
        locations[n] = PrevLocation;

        int n1 = 0;
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] != 0){
                n1++;
            }
        }


        average = Double.parseDouble(df.format(Arrays.stream(ratings).sum()/n1));


    }
    public double getAverage() {
        return average;
    }
}
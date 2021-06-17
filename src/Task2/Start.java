package Task2;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for input and verify data
 * and output the minimum cost
 */
public class Start {
    private static final int MAX_NUMBER_OF_CITIES = 10000;
    private static final int MAX_LENGTH_OF_CITY_NAME = 10;
    private static final long MAX_COST = 200000;
    private static final int MAX_NUMBER_OF_TESTS = 10;
    private static final int MAX_NUMBER_OF_PATHS_TO_FIND = 100;

    public static void dataInput(){
        Scanner in = new Scanner(System.in);
        /**
         * Entering and checking the number of tests
         */
        int numberOfTests = in.nextInt();
        in.nextLine();
        if(numberOfTests > MAX_NUMBER_OF_TESTS){
            closure();
        }
        while(numberOfTests != 0){
            String output = "";
            /**
             * Entering and checking the number of cities
             */
            int numberOfCities = in.nextInt();
            in.nextLine();
            Algorithm algorithm = new Algorithm(numberOfCities);
            int counter = numberOfCities;
            int cityIndex = 1;
            City[] cities = new City[numberOfCities];
            int c = 0;
            if(numberOfCities > 0 & numberOfCities <= MAX_NUMBER_OF_CITIES){
                while(counter != 0){
                    String cityName = setCityName(in);
                    int neighbours = setNeighbours(in, numberOfCities);
                    City newCity = new City(cityIndex, cityName, neighbours);
                    pricesInput(in, newCity, numberOfCities);
                    cities[c] = newCity;
                    c++;
                    algorithm.addToStorage(newCity);
                    cityIndex++;
                    counter--;
                }
                /**
                 * Entering and checking the number of paths to find
                 */
                int numberOfPaths = in.nextInt();
                in.nextLine();
                if (numberOfPaths <= MAX_NUMBER_OF_PATHS_TO_FIND){
                    /**
                     * Entering cities to find minimum costs
                     */
                    for(int i = 0; i < numberOfPaths; i++){
                        String paths =  in.nextLine();
                        output += getOutput(paths, cities, algorithm);
                    }
                }
                else{
                    closure();
                }
                /**
                 * Data output
                 */
                System.out.println(output);
            }
            else {
                closure();
            }
            numberOfTests--;
        }
        in.close();
    }

    /**
     * Finding the minimum cost and writing to a string for output
     */
    private static String getOutput(String paths, City[] cities, Algorithm algorithm){
        String[] array = new String[2];
        int i = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(paths);
        while(i != 2){
            if(matcher.find()){
                array[i] = matcher.group();
                i++;
            }
        }
        String city1 = array[0];
        String city2 = array[1];

        int city1Index = citySearch(cities, city1);
        int city2Index = citySearch(cities, city2);
        long minCost = algorithm.getMinCost(cities[city1Index], cities[city2Index]);
        return minCost + "\n";
    }

    /**
     * Method for entering and checking costs
     */
    private static void pricesInput(Scanner in, City newCity, int numberOfCities){
        for(int i = 0; i < newCity.getNeighbours(); i++){

            String indexAndCost = in.nextLine();

            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(indexAndCost);
            int neighbourIndex = 0;
            long cost = 0;
            if(matcher.find()){
                neighbourIndex = Integer.parseInt(matcher.group());
            }
            if(matcher.find()){
                cost = Long.parseLong(matcher.group());
            }

            if(cost > MAX_COST | cost <= 0){
                closure();
            }
            if(newCity.getPrices() == null){
                newCity.createPrices(numberOfCities);
            }
            if(neighbourIndex >= 1 & neighbourIndex <= newCity.prices.length & neighbourIndex != newCity.getIndex()){
                newCity.setCost(neighbourIndex, cost);
            }
            else{
                closure();
            }
        }
    }
    private static int setNeighbours(Scanner in, int numberOfCities){
        int neighbours = in.nextInt();
        in.nextLine();
        if(neighbours >= 0 & neighbours < numberOfCities){
            return neighbours;
        }
        else{
            closure();
            return -1;
        }
    }
    private static String setCityName(Scanner in){
        String cityName = in.nextLine();
        if(isAlpha(cityName) & cityName.length() <= MAX_LENGTH_OF_CITY_NAME){
            return cityName;
        }
        else{
            closure();
            return "";
        }
    }

    /**
     * Search for a city by name in an array of all cities
     */
    private static int citySearch(City[] cities, String cityName) {
        for (int i = 0; i < cities.length; i++){
            if(cities[i].getName().equals(cityName)){
                return i;
            }
        }
        return -1;
    }
    private static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method for the case of entering incorrect data
     */
    private static void closure(){
        System.out.println("Incorrect data ! Try again !");
        System.exit(0);
    }
}


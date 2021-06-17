package Task2;
import java.util.Arrays;
/**
 * Dijkstra's algorithm
 */
public class Algorithm {
    int numberOfCities;
    private static final long MAX_VALUE = Long.MAX_VALUE;
    private final long[] maxArray;
    /**
     * Dynamic array. The values of the elements change as the methods are used.
     */
    private final long[] minPrices;
    /**
     * The array stores the values of the minimum prices to other cities. Initially filled -1.
     */
    private final boolean[] controlPoints;
    /**
     * The array helps to understand what the minimum costs for the road to cities are already known to us.
     */
    private final long[][] storage;
    /**
     * The array stores the costs entered by the user.
     */

    public Algorithm(int numberOfCities){
        this.numberOfCities = numberOfCities;

        maxArray = new long[numberOfCities];
        Arrays.fill(maxArray, MAX_VALUE);

        minPrices = new long[numberOfCities];
        Arrays.fill(minPrices, -1);

        controlPoints = new boolean[numberOfCities];
        Arrays.fill(controlPoints, false);

        storage = new long[numberOfCities][numberOfCities];
    }
    /**
     * Dijkstra's algorithm
     */
    public void addToStorage(City newCity){
        storage[newCity.getIndex() - 1] = newCity.getPrices();
    }

    public long getMinCost(City cityFrom, City cityTo){
        clearArrays();
        int currentValue = cityFrom.getIndex() - 1;
        long currentCost = 0;
        while(minPrices[cityTo.getIndex() - 1] == (-1)){
            controlPoints[currentValue] = true;
            minPrices[currentValue] = currentCost;
            if(minPrices[cityTo.getIndex() - 1] != (-1)){
                return minPrices[cityTo.getIndex() - 1];
            }
            arraysAddition(maxArray, storage[currentValue], currentCost);
            currentValue = minInArray(maxArray, controlPoints);
            currentCost = maxArray[currentValue];
        }
        return -1;
    }
    private void arraysAddition(long[] maxArray, long[] storage, long minCost){
        long cost;
        for(int i = 0; i < storage.length; i++){
            if(storage[i] != (-1)){
                cost = storage[i] + minCost;
                if(cost < maxArray[i]){
                    maxArray[i] = cost;
                }
            }
        }
    }
    private int minInArray(long[] maxArray, boolean[] controlPoints){
        long minValue = MAX_VALUE;
        int minIndex = 0;
        for(int i = 0; i < maxArray.length; i++){
            if(!controlPoints[i] & maxArray[i] < minValue){
                minValue = maxArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    private void clearArrays(){
        Arrays.fill(maxArray, MAX_VALUE);
        Arrays.fill(minPrices, -1);
        Arrays.fill(controlPoints, false);
    }
    /**
     * Method cleanses arrays to search for a new minimum cost.
     */
}


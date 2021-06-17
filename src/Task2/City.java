package Task2;
import java.util.Arrays;
/**
 *The class stores information about the city
 */

public class City {
    int index;
    String name;
    int neighbours;
    long[] prices;
    /**The array stores the cost of trips to neighboring cities.
     */

    public int getIndex() {
        return index;
    }
    public String getName() {
        return name;
    }
    public int getNeighbours() {
        return neighbours;
    }
    public long[] getPrices() {
        return prices;
    }
    public City(int index, String name, int neighbours){
        this.index = index;
        this.name = name;
        this.neighbours = neighbours;
    }

    public void createPrices(int numberOfCities){
        prices = new long[numberOfCities];
        Arrays.fill(prices, -1);
    }
    /**
     * Method fills the array long[] prices.
     * If the road does not exist or the index matches the index of the current city, -1 is used.
     */

    public void setCost(int neighbourIndex, long cost){
        this.prices[neighbourIndex - 1] = cost;
    }
}


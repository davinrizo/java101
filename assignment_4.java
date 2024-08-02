import java.util.ArrayList;
import java.util.List;

public class StockDataAnalysis{
  public static void main(String[] args) {
    int[] stockPrices = {33, 123, 99, 1020, 901, 5954, 3132, 99, 1020, 1020};

    ArrayList<Integer> stockPricesList = new ArrayList<>();
    for(int price: stockPrices){
      stockPricesList.add(price);;
    }

    int singlePrice = 1020;
    
    double averagePrice = calculateAveragePrice(stockPrices);
    int maxPrice = findMaximumPrice(stockPrices);
    int occurrences = countOccurrences(stockPrices, singlePrice);
    List<Integer> cumulativeSum = computeCumulativeSum(stockPricesList);


    System.out.println("Average stock price: " + averagePrice);
    System.out.println("Maximum stock price: " + maxPrice);
    System.out.println("Occurrences of price " + singlePrice + ": " + occurrences);
    System.out.println("Cummulative sum of price: " + cumulativeSum);


  }

  public static double calculateAveragePrice(int[] prices){
    double sum = 0;
    for(int price: prices){
      sum += price;
    }
    return sum / prices.length;
  }

  public static int findMaximumPrice(int[] prices){
    int maxPrice = prices[0];
    for (int price: prices){
      if(price> maxPrice){
        maxPrice = price;
      }
    }
    return maxPrice;
  }

  public static int countOccurrences(int[] prices, int x){
    //init data count whenever x value in the stock price list.
    int count = 0;
    //Loop through the stock price list to find the specific input x 
    for(int price: prices){
      if(price == x){
        //Iterate the count variable whenever the target price x appears in the array
        count++;
      }
    }
    return count;
  }

  public static List<Integer> computeCumulativeSum(ArrayList<Integer> prices){
    //create a new ArrayList for our cumulative sum
    List<Integer> cumulativeSum = new ArrayList<>();
    int sum = 0;
    //iterates through the array list, maintaining a total sum that is
    //updated with each price in the list.
    for(int price: prices){
      sum += price;
      //The cumulative total at each step is stored in the cumulativeSum variable
      cumulativeSum.add(sum);
    }
    return cumulativeSum;
  }

}


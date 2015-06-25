package prob.chap6;

/**
 *
 * @author Dhval
 */
public class ShareBuySell {

    /**
     * price of share, when stock opens
     */
    static int [] prices = {20,3,4,7,12,5,12,15,1,9,14,0,5,9};
    
    public static void main(String[] s) {
        int days = prices.length;
        int min_value = prices[0];
        int max_profit = 0;
        
        for(int i =1; i < days; i++) {
            if (min_value > prices[i]) {
                min_value = prices[i];
            } else if(max_profit < (prices[i] - min_value)) {
                max_profit = prices[i] - min_value;
                System.out.println("Profit - " + max_profit + " Buy - " + min_value + " Sell - " + prices[i]);
            } 
        }
        
    }
    
}

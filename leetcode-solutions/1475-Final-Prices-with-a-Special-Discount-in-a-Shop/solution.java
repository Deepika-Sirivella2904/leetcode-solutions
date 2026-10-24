/**
 * Problem: 1475. Final Prices with a Special Discount in a Shop
 * Link: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/submissions/1646106333/
 */

class Solution {
    public int[] finalPrices(int[] prices) {
        int[] r=new int[prices.length];
        for(int i=0;i<prices.length;i++){
            int c=prices[i];
            for(int j=i+1;j<prices.length;j++){
                if( prices[j] <= prices[i]){
                    c=prices[i]-prices[j];
                    break;
                }
            }
            r[i]=c;
        }
        return r;
    }
}

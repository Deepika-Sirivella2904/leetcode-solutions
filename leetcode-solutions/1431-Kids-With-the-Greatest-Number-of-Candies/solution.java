/**
 * Problem: 1431. Kids With the Greatest Number of Candies
 * Link: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/submissions/1648177429/
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> r=new ArrayList<>();
        int max=candies[0];
        for(int i=1;i<candies.length;i++){
            if(candies[i]>max){
                max=candies[i];
            }
        }
        for(int i=0;i<candies.length;i++){
            if(extraCandies+candies[i]>=max){
                r.add(true);
            }
            else{
                r.add(false);
            }
        }
        return r;
    }
}

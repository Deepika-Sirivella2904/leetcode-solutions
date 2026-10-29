/**
 * Problem: 2367. Number of Arithmetic Triplets
 * Link: https://leetcode.com/problems/number-of-arithmetic-triplets/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/number-of-arithmetic-triplets/submissions/1649107986/
 */

class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        int c=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    if((nums[j] - nums[i] == diff)&&(nums[k] - nums[j] == diff)){
                        c++;
                    }
                }
            }
        }
        return c;
    }
}

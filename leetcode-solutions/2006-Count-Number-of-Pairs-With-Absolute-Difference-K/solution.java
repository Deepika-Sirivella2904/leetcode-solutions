/**
 * Problem: 2006. Count Number of Pairs With Absolute Difference K
 * Link: https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/submissions/1651808985/
 */

class Solution {
    public int countKDifference(int[] nums, int k) {
        int c=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int r=nums[i]-nums[j];
                if(r<0){
                    if(nums[j]-nums[i]==k){
                        c++;
                    }
                }
                else{
                     if(nums[i]-nums[j]==k){
                        c++;
                    }
                }
            }
        }
        return c;
    }
}

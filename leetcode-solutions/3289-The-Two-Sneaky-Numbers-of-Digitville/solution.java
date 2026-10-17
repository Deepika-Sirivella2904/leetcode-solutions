/**
 * Problem: 3289. The Two Sneaky Numbers of Digitville
 * Link: https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/submissions/1632903977/
 */

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] res=new int[2];
        int ind=0;
            for(int j=0;j<nums.length;j++){
                int c=0;
                for(int k=j+1;k<nums.length;k++){
                    if(nums[j]==nums[k]){
                        c++;
                    }
                }
                if(c==1){
                    res[ind]=nums[j];
                    ind++;
                    
                }
            }
            if(res[0]>res[1]){
                int tem=res[0];
                res[0]=res[1];
                res[1]=tem;
                return res;
            }
            else{
                return res;
            }
    }
}

/**
 * Problem: 2824. Count Pairs Whose Sum is Less than Target
 * Link: https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/submissions/1646970689/
 */

import java.util.List;

class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int r=0;
        for(int i=0;i<nums.size();i++){
            int s=0;
            for(int j=i+1;j<nums.size();j++){
                s=nums.get(i)+nums.get(j);
                if(s<target){
                    r++;
                }
            }
        }
        return r;
    }
}

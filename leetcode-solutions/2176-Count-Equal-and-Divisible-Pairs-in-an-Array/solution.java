/**
 * LeetCode Problem: Count Equal and Divisible Pairs in an Array
 * Problem Link: https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
 * Difficulty: Easy
 * Date Solved: 2026-08-12
 * Submission Link: https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/submissions/1609251698/
 */

class Solution {
    public int countPairs(int[] nums, int k) {
        int c=0;
       for(int i=0;i<nums.length;i++){
           for(int j=i+1;j<nums.length;j++){
               if(nums[i]==nums[j]&&i*j%k==0){
                  c++;
               }
           }
       }
       return c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [3,1,2,2,2,1,3], k = 2
        int[] nums1 = {3, 1, 2, 2, 2, 1, 3};
        int result1 = solution.countPairs(nums1, 2);
        System.out.println("Test 1: nums = [3,1,2,2,2,1,3], k = 2 => " + result1); // 4
        
        // Test case 2: nums = [1,2,3,4], k = 1
        int[] nums2 = {1, 2, 3, 4};
        int result2 = solution.countPairs(nums2, 1);
        System.out.println("Test 2: nums = [1,2,3,4], k = 1 => " + result2); // 0
        
        System.out.println("All test cases completed!");
    }
}

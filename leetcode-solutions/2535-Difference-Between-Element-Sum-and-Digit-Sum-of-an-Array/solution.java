/**
 * LeetCode Problem: Difference Between Element Sum and Digit Sum of an Array
 * Problem Link: https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array/
 * Difficulty: Easy
 * Date Solved: 2026-08-25
 * Submission Link: https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array/submissions/1620363291/
 */

class Solution {
    public int differenceOfSum(int[] nums) {
        int ele=0;
        int dig=0;
        int r=0;
        for(int i=0;i<nums.length;i++){
            ele+=nums[i];
            int n=nums[i];
            while(n>0){
               int rem=n%10;
               dig=dig+rem;
               n=n/10;
           }
       }
       r=ele-dig;
       if(r<0){
           r=dig-ele;
       }
       return r;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [1,15,6,3]
        int[] nums1 = {1, 15, 6, 3};
        int result1 = solution.differenceOfSum(nums1);
        System.out.println("Test 1: nums = [1,15,6,3] => " + result1); // 9
        
        // Test case 2: nums = [1,2,3,4]
        int[] nums2 = {1, 2, 3, 4};
        int result2 = solution.differenceOfSum(nums2);
        System.out.println("Test 2: nums = [1,2,3,4] => " + result2); // 0
        
        System.out.println("All test cases completed!");
    }
}

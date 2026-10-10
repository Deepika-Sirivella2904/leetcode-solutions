/**
 * LeetCode Problem: Find Numbers with Even Number of Digits
 * Problem Link: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 * Difficulty: Easy
 * Date Solved: 2026-10-11
 * Submission Link: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/submissions/1622138555/
 */

class Solution {
    public int findNumbers(int[] nums) {
        int r=0;
       for(int i=0;i<nums.length;i++){
           int c=0;
           int n=nums[i];
           while(n>0){
               c++;
               n=n/10;
           }
           if(c%2==0){
               r++;
           }
       }
       return r;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [12,345,2,6,7896]
        int[] nums1 = {12, 345, 2, 6, 7896};
        int result1 = solution.findNumbers(nums1);
        System.out.println("Test 1: nums = [12,345,2,6,7896] => " + result1); // 2
        
        // Test case 2: nums = [555,901,482,1771]
        int[] nums2 = {555, 901, 482, 1771};
        int result2 = solution.findNumbers(nums2);
        System.out.println("Test 2: nums = [555,901,482,1771] => " + result2); // 1
        
        // Test case 3: nums = [0]
        int[] nums3 = {0};
        int result3 = solution.findNumbers(nums3);
        System.out.println("Test 3: nums = [0] => " + result3); // 0
        
        System.out.println("All test cases completed!");
    }
}

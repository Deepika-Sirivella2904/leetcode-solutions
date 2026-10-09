/**
 * LeetCode Problem: Maximum Product of Two Elements in an Array
 * Problem Link: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
 * Difficulty: Easy
 * Date Solved: 2026-10-10
 * Submission Link: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/submissions/1621160208/
 */

class Solution {
    public int maxProduct(int[] nums) {
        int max=nums[0];
        int ind=0;
        for(int i=1;i<nums.length;i++){
            if(max<nums[i]){
                max=nums[i];
                ind=i;
            }
        }
        nums[ind]=0;
        int nmax=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nmax<nums[i]){
                nmax=nums[i];
            }
        }
        return (max-1)*(nmax-1);
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [3,4,5,2]
        int[] nums1 = {3, 4, 5, 2};
        int result1 = solution.maxProduct(nums1);
        System.out.println("Test 1: nums = [3,4,5,2] => " + result1); // 12
        
        // Test case 2: nums = [1,5,4,5]
        int[] nums2 = {1, 5, 4, 5};
        int result2 = solution.maxProduct(nums2);
        System.out.println("Test 2: nums = [1,5,4,5] => " + result2); // 16
        
        // Test case 3: nums = [3,7]
        int[] nums3 = {3, 7};
        int result3 = solution.maxProduct(nums3);
        System.out.println("Test 3: nums = [3,7] => " + result3); // 12
        
        System.out.println("All test cases completed!");
    }
}

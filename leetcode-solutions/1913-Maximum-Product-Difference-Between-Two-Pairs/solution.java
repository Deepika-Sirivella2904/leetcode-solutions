/**
 * LeetCode Problem: Maximum Product Difference Between Two Pairs
 * Problem Link: https://leetcode.com/problems/maximum-product-difference-between-two-pairs/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-27
 * Submission Link: https://leetcode.com/problems/maximum-product-difference-between-two-pairs/submissions/1587195680/
 */

import java.util.Arrays;

class Solution {
    /**
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     * 
     * Find the maximum product difference between two pairs.
     * Product difference = (a * b) - (c * d) where a,b,c,d are distinct indices.
     * Approach: Sort and use smallest two and largest two numbers.
     * 
     * @param nums Array of integers
     * @return int Maximum product difference
     */
    public int maxProductDifference(int[] nums) {
        if (nums == null || nums.length < 4) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        // Maximum product difference = (largest * second largest) - (smallest * second smallest)
        int n = nums.length;
        return (nums[n - 1] * nums[n - 2]) - (nums[0] * nums[1]);
    }
    
    /**
     * Alternative solution without sorting (O(n) time)
     */
    public int maxProductDifferenceOptimized(int[] nums) {
        if (nums == null || nums.length < 4) {
            return 0;
        }
        
        int max1 = Integer.MIN_VALUE; // Largest
        int max2 = Integer.MIN_VALUE; // Second largest
        int min1 = Integer.MAX_VALUE; // Smallest
        int min2 = Integer.MAX_VALUE; // Second smallest
        
        for (int num : nums) {
            // Update max values
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
            
            // Update min values
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        
        return (max1 * max2) - (min1 * min2);
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [5,6,2,7,4] -> (7*6) - (2*4) = 42 - 8 = 34
        int[] nums1 = {5,6,2,7,4};
        int result1 = solution.maxProductDifference(nums1);
        System.out.println("Test 1: [5,6,2,7,4] -> " + result1); // 34
        
        // Test case 2: [4,2,5,9,7,4,8] -> (9*8) - (2*4) = 72 - 8 = 64
        int[] nums2 = {4,2,5,9,7,4,8};
        int result2 = solution.maxProductDifference(nums2);
        System.out.println("Test 2: [4,2,5,9,7,4,8] -> " + result2); // 64
        
        // Test case 3: [1,2,3,4] -> (4*3) - (1*2) = 12 - 2 = 10
        int[] nums3 = {1,2,3,4};
        int result3 = solution.maxProductDifference(nums3);
        System.out.println("Test 3: [1,2,3,4] -> " + result3); // 10
        
        // Test case 4: [1,1,1,1] -> (1*1) - (1*1) = 1 - 1 = 0
        int[] nums4 = {1,1,1,1};
        int result4 = solution.maxProductDifference(nums4);
        System.out.println("Test 4: [1,1,1,1] -> " + result4); // 0
        
        // Test case 5: [10,10,10,10] -> (10*10) - (10*10) = 100 - 100 = 0
        int[] nums5 = {10,10,10,10};
        int result5 = solution.maxProductDifference(nums5);
        System.out.println("Test 5: [10,10,10,10] -> " + result5); // 0
        
        // Test case 6: [1,2,3,4,5,6] -> (6*5) - (1*2) = 30 - 2 = 28
        int[] nums6 = {1,2,3,4,5,6};
        int result6 = solution.maxProductDifference(nums6);
        System.out.println("Test 6: [1,2,3,4,5,6] -> " + result6); // 28
        
        // Test case 7: [100,1,1,100] -> (100*100) - (1*1) = 10000 - 1 = 9999
        int[] nums7 = {100,1,1,100};
        int result7 = solution.maxProductDifference(nums7);
        System.out.println("Test 7: [100,1,1,100] -> " + result7); // 9999
        
        // Test case 8: [0,0,0,0] -> (0*0) - (0*0) = 0 - 0 = 0
        int[] nums8 = {0,0,0,0};
        int result8 = solution.maxProductDifference(nums8);
        System.out.println("Test 8: [0,0,0,0] -> " + result8); // 0
        
        // Test case 9: [-5,-4,-3,-2] -> (-2*-3) - (-5*-4) = 6 - 20 = -14
        int[] nums9 = {-5,-4,-3,-2};
        int result9 = solution.maxProductDifference(nums9);
        System.out.println("Test 9: [-5,-4,-3,-2] -> " + result9); // -14
        
        // Test case 10: [1,10,2,9] -> (10*9) - (1*2) = 90 - 2 = 88
        int[] nums10 = {1,10,2,9};
        int result10 = solution.maxProductDifference(nums10);
        System.out.println("Test 10: [1,10,2,9] -> " + result10); // 88
        
        // Test optimized method
        System.out.println("\nTesting optimized method:");
        System.out.println("Optimized Test 1: [5,6,2,7,4] -> " + solution.maxProductDifferenceOptimized(nums1)); // 34
        
        System.out.println("All test cases completed!");
    }
}

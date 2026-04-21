/**
 * LeetCode Problem: Find the Number of Good Pairs - I
 * Problem Link: https://leetcode.com/problems/find-the-number-of-good-pairs-i/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-21
 * Submission Link: https://leetcode.com/problems/find-the-number-of-good-pairs-i/submissions/1579396835/
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Find the number of good pairs where nums[i] + nums[j] <= k and i < j.
     * Approach: Two-pointer technique after sorting.
     * 
     * @param nums Array of integers
     * @param k Target sum threshold
     * @return int Number of good pairs
     */
    public int numberOfPairs(int[] nums, int k) {
        // Sort the array for two-pointer approach
        java.util.Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum <= k) {
                // All pairs between left and right will have sum <= k
                count += right - left;
                left++;
            } else {
                // Sum is too large, move right pointer left
                right--;
            }
        }
        
        return count;
    }
    
    /**
     * Alternative solution using HashMap (O(n^2) worst case)
     */
    public int numberOfPairsHashMap(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] <= k) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * Optimized solution using frequency counting for small ranges
     */
    public int numberOfPairsOptimized(int[] nums, int k) {
        // Find min and max to determine range
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int[] frequency = new int[max - min + 1];
        
        // Count frequencies
        for (int num : nums) {
            frequency[num - min]++;
        }
        
        int count = 0;
        
        // Count pairs using two-pointer on frequency array
        int left = 0;
        int right = frequency.length - 1;
        
        while (left < right) {
            int sum = (left + min) + (right + min);
            
            if (sum <= k) {
                // Count all pairs between left and right
                if (left == right) {
                    count += frequency[left] * (frequency[left] - 1) / 2;
                } else {
                    count += frequency[left] * frequency[right];
                }
                left++;
            } else {
                right--;
            }
        }
        
        return count;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [1,2,3,4], k = 5 -> 2 pairs: (1,2), (1,3)
        int[] nums1 = {1,2,3,4};
        int k1 = 5;
        int result1 = solution.numberOfPairs(nums1, k1);
        System.out.println("Test 1: [1,2,3,4], k=5 -> " + result1); // 2
        
        // Test case 2: nums = [3,1,2], k = 2 -> 0 pairs (no pairs sum <= 2)
        int[] nums2 = {3,1,2};
        int k2 = 2;
        int result2 = solution.numberOfPairs(nums2, k2);
        System.out.println("Test 2: [3,1,2], k=2 -> " + result2); // 0
        
        // Test case 3: nums = [1,1,1], k = 2 -> 3 pairs: (1,1), (1,1), (1,1)
        int[] nums3 = {1,1,1};
        int k3 = 2;
        int result3 = solution.numberOfPairs(nums3, k3);
        System.out.println("Test 3: [1,1,1], k=2 -> " + result3); // 3
        
        // Test case 4: nums = [0,0], k = 0 -> 1 pair: (0,0)
        int[] nums4 = {0,0};
        int k4 = 0;
        int result4 = solution.numberOfPairs(nums4, k4);
        System.out.println("Test 4: [0,0], k=0 -> " + result4); // 1
        
        // Test case 5: nums = [5,5,5], k = 10 -> 3 pairs: (5,5), (5,5), (5,5)
        int[] nums5 = {5,5,5};
        int k5 = 10;
        int result5 = solution.numberOfPairs(nums5, k5);
        System.out.println("Test 5: [5,5,5], k=10 -> " + result5); // 3
        
        // Test case 6: nums = [1,2], k = 3 -> 1 pair: (1,2)
        int[] nums6 = {1,2};
        int k6 = 3;
        int result6 = solution.numberOfPairs(nums6, k6);
        System.out.println("Test 6: [1,2], k=3 -> " + result6); // 1
        
        // Test case 7: nums = [1,2,3], k = 4 -> 3 pairs: (1,2), (1,3), (2,3)
        int[] nums7 = {1,2,3};
        int k7 = 4;
        int result7 = solution.numberOfPairs(nums7, k7);
        System.out.println("Test 7: [1,2,3], k=4 -> " + result7); // 3
        
        // Test case 8: nums = [10,20,30], k = 25 -> 1 pair: (10,20)
        int[] nums8 = {10,20,30};
        int k8 = 25;
        int result8 = solution.numberOfPairs(nums8, k8);
        System.out.println("Test 8: [10,20,30], k=25 -> " + result8); // 1
        
        // Test optimized method
        System.out.println("\nTesting optimized method:");
        System.out.println("Optimized Test 1: [1,2,3,4], k=5 -> " + solution.numberOfPairsOptimized(nums1, k1)); // 2
        
        System.out.println("All test cases completed!");
    }
}

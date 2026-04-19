/**
 * LeetCode Problem: Divide Array Into Equal Pairs
 * Problem Link: https://leetcode.com/problems/divide-array-into-equal-pairs/
 * Difficulty: Easy
 * Date Solved: 2026-04-19
 * Submission Link: https://leetcode.com/problems/divide-array-into-equal-pairs/submissions/1576802477/
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Divide array into equal pairs and count the number of pairs.
     * Approach: Count frequency of each number and sum floor(count/2).
     * 
     * @param nums Array of integers
     * @return int Number of pairs that can be formed
     */
    public int divideArray(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count frequency of each number
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Count pairs from frequencies
        int totalPairs = 0;
        for (int count : frequencyMap.values()) {
            totalPairs += count / 2;
        }
        
        return totalPairs;
    }
    
    /**
     * Alternative solution using array for frequency (when numbers are small)
     */
    public int divideArrayOptimized(int[] nums) {
        // Find max to determine array size
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        int[] frequency = new int[max + 1];
        
        // Count frequency
        for (int num : nums) {
            frequency[num]++;
        }
        
        // Count pairs
        int totalPairs = 0;
        for (int count : frequency) {
            totalPairs += count / 2;
        }
        
        return totalPairs;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [3,2,1,4] -> 2 pairs: (3,2), (1,4)
        int[] nums1 = {3,2,1,4};
        int result1 = solution.divideArray(nums1);
        System.out.println("Test 1: [3,2,1,4] -> " + result1); // 2
        
        // Test case 2: [1,3] -> 0 pairs (cannot form pairs)
        int[] nums2 = {1,3};
        int result2 = solution.divideArray(nums2);
        System.out.println("Test 2: [1,3] -> " + result2); // 0
        
        // Test case 3: [2,2,1,1] -> 2 pairs: (2,2), (1,1)
        int[] nums3 = {2,2,1,1};
        int result3 = solution.divideArray(nums3);
        System.out.println("Test 3: [2,2,1,1] -> " + result3); // 2
        
        // Test case 4: [1,1,1,1] -> 2 pairs: (1,1), (1,1)
        int[] nums4 = {1,1,1,1};
        int result4 = solution.divideArray(nums4);
        System.out.println("Test 4: [1,1,1,1] -> " + result4); // 2
        
        // Test case 5: [1] -> 0 pairs (single element)
        int[] nums5 = {1};
        int result5 = solution.divideArray(nums5);
        System.out.println("Test 5: [1] -> " + result5); // 0
        
        // Test case 6: [] -> 0 pairs (empty array)
        int[] nums6 = {};
        int result6 = solution.divideArray(nums6);
        System.out.println("Test 6: [] -> " + result6); // 0
        
        // Test case 7: [5,5,5,5,5,5] -> 2 pairs (5 left over)
        int[] nums7 = {5,5,5,5,5,5};
        int result7 = solution.divideArray(nums7);
        System.out.println("Test 7: [5,5,5,5,5,5] -> " + result7); // 2
        
        // Test case 8: [1,2,3,4,5,6,7,8] -> 4 pairs: (1,2), (3,4), (5,6), (7,8)
        int[] nums8 = {1,2,3,4,5,6,7,8};
        int result8 = solution.divideArray(nums8);
        System.out.println("Test 8: [1,2,3,4,5,6,7,8] -> " + result8); // 4
        
        // Test case 9: [100,100,200,200,300] -> 2 pairs: (100,100), (200,200)
        int[] nums9 = {100,100,200,200,300};
        int result9 = solution.divideArray(nums9);
        System.out.println("Test 9: [100,100,200,200,300] -> " + result9); // 2
        
        // Test optimized method
        System.out.println("\nTesting optimized method:");
        System.out.println("Optimized Test 1: [3,2,1,4] -> " + solution.divideArrayOptimized(nums1)); // 2
        
        System.out.println("All test cases completed!");
    }
}

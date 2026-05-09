/**
 * LeetCode Problem: Number Of Good Pairs
 * Problem Link: https://leetcode.com/problems/number-of-good-pairs/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-09
 * Submission Link: https://leetcode.com/problems/number-of-good-pairs/submissions/1599224741/
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Count the number of good pairs.
     * A good pair is defined as (i, j) where i < j and nums[i] == nums[j].
     * Approach: Use frequency map to count occurrences and calculate pairs.
     * 
     * @param nums Array of integers
     * @return int Number of good pairs
     */
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int goodPairs = 0;
        
        // Count frequency of each number
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Calculate good pairs: for each frequency f, number of pairs = f * (f-1) / 2
        for (int frequency : frequencyMap.values()) {
            if (frequency >= 2) {
                goodPairs += frequency * (frequency - 1) / 2;
            }
        }
        
        return goodPairs;
    }
    
    /**
     * Alternative solution using single pass
     */
    public int numIdenticalPairsSinglePass(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int goodPairs = 0;
        
        // As we encounter each number, add the current frequency to goodPairs
        // because this number can form pairs with all previous occurrences
        for (int num : nums) {
            int currentFrequency = frequencyMap.getOrDefault(num, 0);
            goodPairs += currentFrequency;
            frequencyMap.put(num, currentFrequency + 1);
        }
        
        return goodPairs;
    }
    
    /**
     * Alternative solution using array (when numbers are small)
     */
    public int numIdenticalPairsArray(int[] nums) {
        int[] frequency = new int[101]; // Assuming numbers are between 0 and 100
        int goodPairs = 0;
        
        for (int num : nums) {
            goodPairs += frequency[num];
            frequency[num]++;
        }
        
        return goodPairs;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [1,2,3,1,1,3] -> 4 pairs
        // Pairs: (0,3), (0,4), (3,4), (2,5)
        int[] nums1 = {1,2,3,1,1,3};
        int result1 = solution.numIdenticalPairs(nums1);
        System.out.println("Test 1: [1,2,3,1,1,3] -> " + result1); // 4
        
        // Test case 2: [1,1,1,1] -> 6 pairs
        // Pairs: (0,1), (0,2), (0,3), (1,2), (1,3), (2,3)
        int[] nums2 = {1,1,1,1};
        int result2 = solution.numIdenticalPairs(nums2);
        System.out.println("Test 2: [1,1,1,1] -> " + result2); // 6
        
        // Test case 3: [1,2,3] -> 0 pairs (no duplicates)
        int[] nums3 = {1,2,3};
        int result3 = solution.numIdenticalPairs(nums3);
        System.out.println("Test 3: [1,2,3] -> " + result3); // 0
        
        // Test case 4: [] -> 0 pairs (empty array)
        int[] nums4 = {};
        int result4 = solution.numIdenticalPairs(nums4);
        System.out.println("Test 4: [] -> " + result4); // 0
        
        // Test case 5: [1] -> 0 pairs (single element)
        int[] nums5 = {1};
        int result5 = solution.numIdenticalPairs(nums5);
        System.out.println("Test 5: [1] -> " + result5); // 0
        
        // Test case 6: [1,2,1,2,1,2] -> 3 pairs for 1s + 3 pairs for 2s = 6 pairs
        int[] nums6 = {1,2,1,2,1,2};
        int result6 = solution.numIdenticalPairs(nums6);
        System.out.println("Test 6: [1,2,1,2,1,2] -> " + result6); // 6
        
        // Test case 7: [5,5,5] -> 3 pairs
        // Pairs: (0,1), (0,2), (1,2)
        int[] nums7 = {5,5,5};
        int result7 = solution.numIdenticalPairs(nums7);
        System.out.println("Test 7: [5,5,5] -> " + result7); // 3
        
        // Test case 8: [1,1] -> 1 pair
        int[] nums8 = {1,1};
        int result8 = solution.numIdenticalPairs(nums8);
        System.out.println("Test 8: [1,1] -> " + result8); // 1
        
        // Test case 9: [1,2,3,4,5,1,2,3,4,5] -> 5 pairs (one for each number)
        int[] nums9 = {1,2,3,4,5,1,2,3,4,5};
        int result9 = solution.numIdenticalPairs(nums9);
        System.out.println("Test 9: [1,2,3,4,5,1,2,3,4,5] -> " + result9); // 5
        
        // Test case 10: [100,100,100,100,100] -> 10 pairs
        // 5 * 4 / 2 = 10
        int[] nums10 = {100,100,100,100,100};
        int result10 = solution.numIdenticalPairs(nums10);
        System.out.println("Test 10: [100,100,100,100,100] -> " + result10); // 10
        
        // Test single pass method
        System.out.println("\nTesting single pass method:");
        System.out.println("Single Pass Test 1: [1,2,3,1,1,3] -> " + solution.numIdenticalPairsSinglePass(nums1)); // 4
        
        // Test array method
        System.out.println("\nTesting array method:");
        System.out.println("Array Test 1: [1,2,3,1,1,3] -> " + solution.numIdenticalPairsArray(nums1)); // 4
        
        System.out.println("All test cases completed!");
    }
}

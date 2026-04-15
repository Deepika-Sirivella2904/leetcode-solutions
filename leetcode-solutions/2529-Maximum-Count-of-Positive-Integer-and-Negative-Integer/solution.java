/**
 * LeetCode Problem: Maximum Count of Positive Integer and Negative Integer
 * Problem Link: https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/description/
 * Difficulty: Medium
 * Date Solved: 2026-04-15
 * Submission Link: https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/submissions/1571656365/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find the maximum count of positive and negative integers in the array.
     * Approach: Count positives and negatives, then find max between them.
     * 
     * @param nums Array of integers
     * @return int Maximum count between positive and negative integers
     */
    public int maximumCount(int[] nums) {
        int positiveCount = 0;
        int negativeCount = 0;
        
        for (int num : nums) {
            if (num > 0) {
                positiveCount++;
            } else if (num < 0) {
                negativeCount++;
            }
            // Note: zeros are neither counted as positive nor negative
        }
        
        return Math.max(positiveCount, negativeCount);
    }
    
    /**
     * Alternative solution using streams (Java 8+)
     */
    public int maximumCountStream(int[] nums) {
        long positiveCount = java.util.Arrays.stream(nums)
            .filter(num -> num > 0)
            .count();
            
        long negativeCount = java.util.Arrays.stream(nums)
            .filter(num -> num < 0)
            .count();
            
        return (int) Math.max(positiveCount, negativeCount);
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [-3,-2,-3,0,1,2] -> 3 (positives: 2, negatives: 3)
        int[] nums1 = {-3,-2,-3,0,1,2};
        int result1 = solution.maximumCount(nums1);
        System.out.println("Test 1: [-3,-2,-3,0,1,2] -> " + result1); // 3
        
        // Test case 2: [-1,-2,-3,-4,3,2,1] -> 3 (positives: 3, negatives: 4)
        int[] nums2 = {-1,-2,-3,-4,3,2,1};
        int result2 = solution.maximumCount(nums2);
        System.out.println("Test 2: [-1,-2,-3,-4,3,2,1] -> " + result2); // 3
        
        // Test case 3: [1,2,3,4,5] -> 5 (positives: 5, negatives: 0)
        int[] nums3 = {1,2,3,4,5};
        int result3 = solution.maximumCount(nums3);
        System.out.println("Test 3: [1,2,3,4,5] -> " + result3); // 5
        
        // Test case 4: [-1,-2,-3,-4,-5] -> 4 (positives: 0, negatives: 4)
        int[] nums4 = {-1,-2,-3,-4,-5};
        int result4 = solution.maximumCount(nums4);
        System.out.println("Test 4: [-1,-2,-3,-4,-5] -> " + result4); // 4
        
        // Test case 5: [0,0,0] -> 0 (positives: 0, negatives: 0)
        int[] nums5 = {0,0,0};
        int result5 = solution.maximumCount(nums5);
        System.out.println("Test 5: [0,0,0] -> " + result5); // 0
        
        // Test case 6: [5,-5] -> 1 (positives: 1, negatives: 1)
        int[] nums6 = {5,-5};
        int result6 = solution.maximumCount(nums6);
        System.out.println("Test 6: [5,-5] -> " + result6); // 1
        
        // Test case 7: [1,-1,1,-1,1] -> 3 (positives: 3, negatives: 2)
        int[] nums7 = {1,-1,1,-1,1};
        int result7 = solution.maximumCount(nums7);
        System.out.println("Test 7: [1,-1,1,-1,1] -> " + result7); // 3
        
        // Test case 8: [100] -> 1 (positives: 1, negatives: 0)
        int[] nums8 = {100};
        int result8 = solution.maximumCount(nums8);
        System.out.println("Test 8: [100] -> " + result8); // 1
        
        // Test case 9: [-100] -> 1 (positives: 0, negatives: 1)
        int[] nums9 = {-100};
        int result9 = solution.maximumCount(nums9);
        System.out.println("Test 9: [-100] -> " + result9); // 1
        
        // Test case 10: [1,1,1,1] -> 4 (positives: 4, negatives: 0)
        int[] nums10 = {1,1,1,1};
        int result10 = solution.maximumCount(nums10);
        System.out.println("Test 10: [1,1,1,1] -> " + result10); // 4
        
        // Test stream method
        System.out.println("\nTesting stream method:");
        System.out.println("Stream Test 1: [-3,-2,-3,0,1,2] -> " + solution.maximumCountStream(nums1)); // 3
        
        System.out.println("All test cases completed!");
    }
}

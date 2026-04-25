/**
 * LeetCode Problem: Find the Highest Altitude
 * Problem Link: https://leetcode.com/problems/find-the-highest-altitude/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-25
 * Submission Link: https://leetcode.com/problems/find-the-highest-altitude/submissions/1583022033/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find the highest altitude reached during the journey.
     * Approach: Single pass tracking current altitude and maximum.
     * 
     * @param gain Array of altitude changes
     * @return int Highest altitude reached
     */
    public int largestAltitude(int[] gain) {
        if (gain == null || gain.length == 0) {
            return 0;
        }
        
        int currentAltitude = 0;
        int maxAltitude = 0;
        
        for (int change : gain) {
            currentAltitude += change;
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        
        return maxAltitude;
    }
    
    /**
     * Alternative solution using prefix sum array
     */
    public int largestAltitudePrefixSum(int[] gain) {
        if (gain == null || gain.length == 0) {
            return 0;
        }
        
        int[] prefixSum = new int[gain.length + 1];
        prefixSum[0] = 0;
        
        for (int i = 0; i < gain.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + gain[i];
        }
        
        int maxAltitude = 0;
        for (int altitude : prefixSum) {
            maxAltitude = Math.max(maxAltitude, altitude);
        }
        
        return maxAltitude;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [-5,1,5,0,-7] -> 1 (altitudes: 0, -5, -4, 1, 1, -6)
        int[] gain1 = {-5,1,5,0,-7};
        int result1 = solution.largestAltitude(gain1);
        System.out.println("Test 1: [-5,1,5,0,-7] -> " + result1); // 1
        
        // Test case 2: [4,-3,2,1,-5] -> 4 (altitudes: 0, 4, 1, 3, 4, -1)
        int[] gain2 = {4,-3,2,1,-5};
        int result2 = solution.largestAltitude(gain2);
        System.out.println("Test 2: [4,-3,2,1,-5] -> " + result2); // 4
        
        // Test case 3: [] -> 0 (no changes, start and end at 0)
        int[] gain3 = {};
        int result3 = solution.largestAltitude(gain3);
        System.out.println("Test 3: [] -> " + result3); // 0
        
        // Test case 4: [0] -> 0 (no change)
        int[] gain4 = {0};
        int result4 = solution.largestAltitude(gain4);
        System.out.println("Test 4: [0] -> " + result4); // 0
        
        // Test case 5: [5] -> 5 (altitudes: 0, 5)
        int[] gain5 = {5};
        int result5 = solution.largestAltitude(gain5);
        System.out.println("Test 5: [5] -> " + result5); // 5
        
        // Test case 6: [-5] -> 0 (altitudes: 0, -5)
        int[] gain6 = {-5};
        int result6 = solution.largestAltitude(gain6);
        System.out.println("Test 6: [-5] -> " + result6); // 0
        
        // Test case 7: [1,2,3,4,5] -> 15 (altitudes: 0, 1, 3, 6, 10, 15)
        int[] gain7 = {1,2,3,4,5};
        int result7 = solution.largestAltitude(gain7);
        System.out.println("Test 7: [1,2,3,4,5] -> " + result7); // 15
        
        // Test case 8: [-1,-2,-3,-4,-5] -> 0 (all negative, never go above 0)
        int[] gain8 = {-1,-2,-3,-4,-5};
        int result8 = solution.largestAltitude(gain8);
        System.out.println("Test 8: [-1,-2,-3,-4,-5] -> " + result8); // 0
        
        // Test case 9: [10,-5,10,-5,10] -> 20 (altitudes: 0, 10, 5, 15, 10, 20)
        int[] gain9 = {10,-5,10,-5,10};
        int result9 = solution.largestAltitude(gain9);
        System.out.println("Test 9: [10,-5,10,-5,10] -> " + result9); // 20
        
        // Test case 10: [2,-1,2,-1,2,-1] -> 4 (altitudes: 0, 2, 1, 3, 2, 4, 3)
        int[] gain10 = {2,-1,2,-1,2,-1};
        int result10 = solution.largestAltitude(gain10);
        System.out.println("Test 10: [2,-1,2,-1,2,-1] -> " + result10); // 4
        
        // Test prefix sum method
        System.out.println("\nTesting prefix sum method:");
        System.out.println("Prefix Sum Test 1: [-5,1,5,0,-7] -> " + solution.largestAltitudePrefixSum(gain1)); // 1
        
        System.out.println("All test cases completed!");
    }
}

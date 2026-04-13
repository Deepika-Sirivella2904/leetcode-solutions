/**
 * LeetCode Problem: Climbing Stairs
 * Problem Link: https://leetcode.com/problems/climbing-stairs/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-13
 * Submission Link: https://leetcode.com/problems/climbing-stairs/submissions/1570560912/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Calculate the number of distinct ways to climb to the top of n stairs.
     * Approach: Dynamic programming with Fibonacci pattern.
     * 
     * @param n Number of stairs
     * @return int Number of distinct ways to climb to the top
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        
        int prev = 1;  // Ways to reach step 1
        int curr = 2;  // Ways to reach step 2
        
        for (int i = 3; i <= n; i++) {
            int next = prev + curr;  // Ways to reach current step
            prev = curr;
            curr = next;
        }
        
        return curr;
    }
    
    /**
     * Alternative solution using recursion with memoization
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        return climbStairsHelper(n, memo);
    }
    
    private int climbStairsHelper(int n, int[] memo) {
        if (n <= 2) {
            return n;
        }
        
        if (memo[n] != 0) {
            return memo[n];
        }
        
        memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        return memo[n];
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: n = 2 -> 2
        System.out.println("Test 1: n = 2 -> " + solution.climbStairs(2)); // 2
        
        // Test case 2: n = 3 -> 3
        System.out.println("Test 2: n = 3 -> " + solution.climbStairs(3)); // 3
        
        // Test case 3: n = 1 -> 1
        System.out.println("Test 3: n = 1 -> " + solution.climbStairs(1)); // 1
        
        // Test case 4: n = 4 -> 5
        System.out.println("Test 4: n = 4 -> " + solution.climbStairs(4)); // 5
        
        // Test case 5: n = 5 -> 8
        System.out.println("Test 5: n = 5 -> " + solution.climbStairs(5)); // 8
        
        // Test case 6: n = 6 -> 13
        System.out.println("Test 6: n = 6 -> " + solution.climbStairs(6)); // 13
        
        // Test case 7: n = 7 -> 21
        System.out.println("Test 7: n = 7 -> " + solution.climbStairs(7)); // 21
        
        // Test case 8: n = 8 -> 34
        System.out.println("Test 8: n = 8 -> " + solution.climbStairs(8)); // 34
        
        // Test case 9: n = 10 -> 89
        System.out.println("Test 9: n = 10 -> " + solution.climbStairs(10)); // 89
        
        // Test case 10: n = 45 -> 1836311903 (max constraint)
        System.out.println("Test 10: n = 45 -> " + solution.climbStairs(45)); // 1836311903
        
        // Test memoization method
        System.out.println("\nTesting memoization method:");
        System.out.println("Memo n = 5 -> " + solution.climbStairsMemo(5)); // 8
        
        System.out.println("All test cases completed!");
    }
}

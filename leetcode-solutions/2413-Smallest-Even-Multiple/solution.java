/**
 * LeetCode Problem: Smallest Even Multiple
 * Problem Link: https://leetcode.com/problems/smallest-even-multiple/
 * Difficulty: Easy
 * Date Solved: 2026-04-28
 * Submission Link: https://leetcode.com/problems/smallest-even-multiple/submissions/1588059373/
 */

class Solution {
    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * Find the smallest positive even multiple of n.
     * If n is even, return n. If n is odd, return 2*n.
     * Approach: Check if n is even, if not multiply by 2.
     * 
     * @param n Positive integer
     * @return int Smallest positive even multiple of n
     */
    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        } else {
            return n * 2;
        }
    }
    
    /**
     * Alternative solution using bitwise AND
     */
    public int smallestEvenMultipleBitwise(int n) {
        // If n is even (last bit is 0), return n
        // If n is odd (last bit is 1), return n * 2
        return (n & 1) == 0 ? n : n * 2;
    }
    
    /**
     * Alternative solution using formula
     * The smallest even multiple is always n if n is even, or 2*n if n is odd
     * This can be simplified to: n * (2 if n is odd else 1)
     * Which is equivalent to: n * (2 - (n % 2))
     */
    public int smallestEvenMultipleFormula(int n) {
        return n * (2 - (n % 2));
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: n = 5 -> 10 (5 is odd, so 5*2 = 10)
        int result1 = solution.smallestEvenMultiple(5);
        System.out.println("Test 1: n = 5 -> " + result1); // 10
        
        // Test case 2: n = 6 -> 6 (6 is even, so return 6)
        int result2 = solution.smallestEvenMultiple(6);
        System.out.println("Test 2: n = 6 -> " + result2); // 6
        
        // Test case 3: n = 1 -> 2 (1 is odd, so 1*2 = 2)
        int result3 = solution.smallestEvenMultiple(1);
        System.out.println("Test 3: n = 1 -> " + result3); // 2
        
        // Test case 4: n = 2 -> 2 (2 is even, so return 2)
        int result4 = solution.smallestEvenMultiple(2);
        System.out.println("Test 4: n = 2 -> " + result4); // 2
        
        // Test case 5: n = 3 -> 6 (3 is odd, so 3*2 = 6)
        int result5 = solution.smallestEvenMultiple(3);
        System.out.println("Test 5: n = 3 -> " + result5); // 6
        
        // Test case 6: n = 4 -> 4 (4 is even, so return 4)
        int result6 = solution.smallestEvenMultiple(4);
        System.out.println("Test 6: n = 4 -> " + result6); // 4
        
        // Test case 7: n = 7 -> 14 (7 is odd, so 7*2 = 14)
        int result7 = solution.smallestEvenMultiple(7);
        System.out.println("Test 7: n = 7 -> " + result7); // 14
        
        // Test case 8: n = 8 -> 8 (8 is even, so return 8)
        int result8 = solution.smallestEvenMultiple(8);
        System.out.println("Test 8: n = 8 -> " + result8); // 8
        
        // Test case 9: n = 9 -> 18 (9 is odd, so 9*2 = 18)
        int result9 = solution.smallestEvenMultiple(9);
        System.out.println("Test 9: n = 9 -> " + result9); // 18
        
        // Test case 10: n = 10 -> 10 (10 is even, so return 10)
        int result10 = solution.smallestEvenMultiple(10);
        System.out.println("Test 10: n = 10 -> " + result10); // 10
        
        // Test bitwise method
        System.out.println("\nTesting bitwise method:");
        System.out.println("Bitwise Test 1: n = 5 -> " + solution.smallestEvenMultipleBitwise(5)); // 10
        
        // Test formula method
        System.out.println("\nTesting formula method:");
        System.out.println("Formula Test 1: n = 5 -> " + solution.smallestEvenMultipleFormula(5)); // 10
        
        System.out.println("All test cases completed!");
    }
}

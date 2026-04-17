/**
 * LeetCode Problem: Pow(x,n)
 * Problem Link: https://leetcode.com/problems/powx-n/description/
 * Difficulty: Medium
 * Date Solved: 2026-04-17
 * Submission Link: https://leetcode.com/problems/powx-n/submissions/1574768364/
 */

class Solution {
    /**
     * Time Complexity: O(log |n|)
     * Space Complexity: O(log |n|)
     * 
     * Calculate x raised to the power n using fast exponentiation.
     * Approach: Binary exponentiation (divide and conquer).
     * 
     * @param x Base number
     * @param n Exponent
     * @return double x raised to the power n
     */
    public double myPow(double x, int n) {
        // Handle edge case where n is Integer.MIN_VALUE
        if (n == Integer.MIN_VALUE) {
            return myPow(x, n + 1) / x;
        }
        
        if (n < 0) {
            return 1.0 / myPow(x, -n);
        }
        
        return myPowHelper(x, n);
    }
    
    /**
     * Helper method for positive exponents using binary exponentiation
     */
    private double myPowHelper(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        
        if (n == 1) {
            return x;
        }
        
        // Recursively calculate half power
        double half = myPowHelper(x, n / 2);
        
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
    
    /**
     * Iterative version using bit manipulation
     */
    public double myPowIterative(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            return myPowIterative(x, n + 1) / x;
        }
        
        if (n < 0) {
            x = 1.0 / x;
            n = -n;
        }
        
        double result = 1.0;
        double current = x;
        
        while (n > 0) {
            if ((n & 1) == 1) {
                result *= current;
            }
            current *= current;
            n >>= 1;
        }
        
        return result;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: x = 2.00000, n = 10 -> 1024.00000
        System.out.println("Test 1: 2.00000^10 -> " + solution.myPow(2.00000, 10)); // 1024.00000
        
        // Test case 2: x = 2.10000, n = 3 -> 9.26100
        System.out.println("Test 2: 2.10000^3 -> " + solution.myPow(2.10000, 3)); // 9.26100
        
        // Test case 3: x = 2.00000, n = -2 -> 0.25000
        System.out.println("Test 3: 2.00000^-2 -> " + solution.myPow(2.00000, -2)); // 0.25000
        
        // Test case 4: x = 1.00000, n = 0 -> 1.00000
        System.out.println("Test 4: 1.00000^0 -> " + solution.myPow(1.00000, 0)); // 1.00000
        
        // Test case 5: x = 0.00000, n = 5 -> 0.00000
        System.out.println("Test 5: 0.00000^5 -> " + solution.myPow(0.00000, 5)); // 0.00000
        
        // Test case 6: x = -2.00000, n = 3 -> -8.00000
        System.out.println("Test 6: -2.00000^3 -> " + solution.myPow(-2.00000, 3)); // -8.00000
        
        // Test case 7: x = -2.00000, n = 2 -> 4.00000
        System.out.println("Test 7: -2.00000^2 -> " + solution.myPow(-2.00000, 2)); // 4.00000
        
        // Test case 8: x = 0.50000, n = -1 -> 2.00000
        System.out.println("Test 8: 0.50000^-1 -> " + solution.myPow(0.50000, -1)); // 2.00000
        
        // Test case 9: x = 1.00000, n = 2147483647 -> 1.00000
        System.out.println("Test 9: 1.00000^2147483647 -> " + solution.myPow(1.00000, 2147483647)); // 1.00000
        
        // Test case 10: x = 2.00000, n = -2147483648 -> 0.00000
        System.out.println("Test 10: 2.00000^-2147483648 -> " + solution.myPow(2.00000, -2147483648)); // 0.00000
        
        // Test iterative method
        System.out.println("\nTesting iterative method:");
        System.out.println("Iterative Test 1: 2.00000^10 -> " + solution.myPowIterative(2.00000, 10)); // 1024.00000
        
        System.out.println("All test cases completed!");
    }
}

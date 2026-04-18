/**
 * LeetCode Problem: Fibonacci Number
 * Problem Link: https://leetcode.com/problems/fibonacci-number/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-18
 * Submission Link: https://leetcode.com/problems/fibonacci-number/submissions/1575933448/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Calculate the nth Fibonacci number.
     * Approach: Iterative DP with constant space.
     * 
     * @param n Index of Fibonacci number
     * @return int nth Fibonacci number
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        
        int prev = 0;
        int curr = 1;
        
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        
        return curr;
    }
    
    /**
     * Alternative solution using recursion with memoization
     */
    public int fibMemo(int n) {
        int[] memo = new int[n + 1];
        return fibHelper(n, memo);
    }
    
    private int fibHelper(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        
        if (memo[n] != 0) {
            return memo[n];
        }
        
        memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
        return memo[n];
    }
    
    /**
     * Alternative solution using matrix exponentiation (O(log n))
     */
    public int fibMatrix(int n) {
        if (n <= 1) {
            return n;
        }
        
        int[][] result = matrixPower(new int[][]{{1, 1}, {1, 0}}, n - 1);
        return result[0][0];
    }
    
    private int[][] matrixPower(int[][] matrix, int power) {
        if (power == 1) {
            return matrix;
        }
        
        int[][] half = matrixPower(matrix, power / 2);
        int[][] result = matrixMultiply(half, half);
        
        if (power % 2 == 1) {
            result = matrixMultiply(result, matrix);
        }
        
        return result;
    }
    
    private int[][] matrixMultiply(int[][] a, int[][] b) {
        return new int[][]{
            {a[0][0] * b[0][0] + a[0][1] * b[1][0], a[0][0] * b[0][1] + a[0][1] * b[1][1]},
            {a[1][0] * b[0][0] + a[1][1] * b[1][0], a[1][0] * b[0][1] + a[1][1] * b[1][1]}
        };
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: n = 2 -> 1
        System.out.println("Test 1: fib(2) -> " + solution.fib(2)); // 1
        
        // Test case 2: n = 3 -> 2
        System.out.println("Test 2: fib(3) -> " + solution.fib(3)); // 2
        
        // Test case 3: n = 4 -> 3
        System.out.println("Test 3: fib(4) -> " + solution.fib(4)); // 3
        
        // Test case 4: n = 0 -> 0
        System.out.println("Test 4: fib(0) -> " + solution.fib(0)); // 0
        
        // Test case 5: n = 1 -> 1
        System.out.println("Test 5: fib(1) -> " + solution.fib(1)); // 1
        
        // Test case 6: n = 5 -> 5
        System.out.println("Test 6: fib(5) -> " + solution.fib(5)); // 5
        
        // Test case 7: n = 6 -> 8
        System.out.println("Test 7: fib(6) -> " + solution.fib(6)); // 8
        
        // Test case 8: n = 7 -> 13
        System.out.println("Test 8: fib(7) -> " + solution.fib(7)); // 13
        
        // Test case 9: n = 10 -> 55
        System.out.println("Test 9: fib(10) -> " + solution.fib(10)); // 55
        
        // Test case 10: n = 30 -> 832040
        System.out.println("Test 10: fib(30) -> " + solution.fib(30)); // 832040
        
        // Test memoization method
        System.out.println("\nTesting memoization method:");
        System.out.println("Memo fib(5) -> " + solution.fibMemo(5)); // 5
        
        // Test matrix method
        System.out.println("\nTesting matrix method:");
        System.out.println("Matrix fib(5) -> " + solution.fibMatrix(5)); // 5
        
        System.out.println("All test cases completed!");
    }
}

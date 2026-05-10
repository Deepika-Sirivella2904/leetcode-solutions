/**
 * LeetCode Problem: Final Value Of Variable After Performing Operations
 * Problem Link: https://leetcode.com/problems/final-value-of-variable-after-performing-operations/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-10
 * Submission Link: https://leetcode.com/problems/final-value-of-variable-after-performing-operations/submissions/1600547239/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Calculate final value of X after performing operations.
     * Operations: X = X + val, X = X - val (ignored)
     * Approach: Sum all positive values, ignore negative ones.
     * 
     * @param operations Array of operations
     * @return int Final value of X
     */
    public int finalValueAfterOperations(int[] operations) {
        int x = 0;
        
        for (int operation : operations) {
            if (operation > 0) {
                x += operation;
            }
            // Negative operations are ignored
        }
        
        return x;
    }
    
    /**
     * Alternative solution using enhanced for loop
     */
    public int finalValueAfterOperationsEnhanced(int[] operations) {
        int x = 0;
        
        for (int i = 0; i < operations.length; i++) {
            int operation = operations[i];
            if (operation > 0) {
                x += operation;
            }
            // Debug: show operation and current X value
            // System.out.println("Operation " + i + ": " + operation + ", X = " + x);
        }
        
        return x;
    }
    
    /**
     * Alternative solution using stream (Java 8+)
     */
    public int finalValueAfterOperationsStream(int[] operations) {
        return java.util.Arrays.stream(operations)
            .filter(op -> op > 0)
            .sum();
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [5,-2,4,3] -> 12 (5 + 4 + 3, ignore -2)
        int[] ops1 = {5,-2,4,3};
        int result1 = solution.finalValueAfterOperations(ops1);
        System.out.println("Test 1: [5,-2,4,3] -> " + result1); // 12
        
        // Test case 2: [3,-1,2,-5,4] -> 9 (3 + 2 + 4, ignore -1 and -5)
        int[] ops2 = {3,-1,2,-5,4};
        int result2 = solution.finalValueAfterOperations(ops2);
        System.out.println("Test 2: [3,-1,2,-5,4] -> " + result2); // 9
        
        // Test case 3: [-5,-2,-3] -> 0 (all negative, all ignored)
        int[] ops3 = {-5,-2,-3};
        int result3 = solution.finalValueAfterOperations(ops3);
        System.out.println("Test 3: [-5,-2,-3] -> " + result3); // 0
        
        // Test case 4: [1,2,3] -> 6 (1 + 2 + 3)
        int[] ops4 = {1,2,3};
        int result4 = solution.finalValueAfterOperations(ops4);
        System.out.println("Test 4: [1,2,3] -> " + result4); // 6
        
        // Test case 5: [0] -> 0 (0 is ignored)
        int[] ops5 = {0};
        int result5 = solution.finalValueAfterOperations(ops5);
        System.out.println("Test 5: [0] -> " + result5); // 0
        
        // Test case 6: [10,-10,10,-10,10] -> 30 (10 + 10 + 10, ignore -10s)
        int[] ops6 = {10,-10,10,-10,10};
        int result6 = solution.finalValueAfterOperations(ops6);
        System.out.println("Test 6: [10,-10,10,-10,10] -> " + result6); // 30
        
        // Test case 7: [100] -> 100
        int[] ops7 = {100};
        int result7 = solution.finalValueAfterOperations(ops7);
        System.out.println("Test 7: [100] -> " + result7); // 100
        
        // Test case 8: [-1] -> 0 (negative ignored)
        int[] ops8 = {-1};
        int result8 = solution.finalValueAfterOperations(ops8);
        System.out.println("Test 8: [-1] -> " + result8); // 0
        
        // Test case 9: [1,-1,1,-1,1] -> 3 (1 + 1 + 1, ignore -1s)
        int[] ops9 = {1,-1,1,-1,1};
        int result9 = solution.finalValueAfterOperations(ops9);
        System.out.println("Test 9: [1,-1,1,-1,1] -> " + result9); // 3
        
        // Test case 10: [50,-25,75,-10,100] -> 225 (50 + 75 + 100, ignore -25 and -10)
        int[] ops10 = {50,-25,75,-10,100};
        int result10 = solution.finalValueAfterOperations(ops10);
        System.out.println("Test 10: [50,-25,75,-10,100] -> " + result10); // 225
        
        // Test enhanced method
        System.out.println("\nTesting enhanced method:");
        System.out.println("Enhanced Test 1: [5,-2,4,3] -> " + solution.finalValueAfterOperationsEnhanced(ops1)); // 12
        
        // Test stream method
        System.out.println("\nTesting stream method:");
        System.out.println("Stream Test 1: [5,-2,4,3] -> " + solution.finalValueAfterOperationsStream(ops1)); // 12
        
        System.out.println("All test cases completed!");
    }
}

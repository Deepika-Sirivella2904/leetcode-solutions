/**
 * LeetCode Problem: Add Two Integers
 * Problem Link: https://leetcode.com/problems/add-two-integers/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-30
 * Submission Link: https://leetcode.com/problems/add-two-integers/submissions/1590196703/
 */

class Solution {
    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * Add two integers and return the sum.
     * Approach: Simple addition operation.
     * 
     * @param num1 First integer
     * @param num2 Second integer
     * @return int Sum of num1 and num2
     */
    public int sum(int num1, int num2) {
        return num1 + num2;
    }
    
    /**
     * Alternative solution using bitwise addition
     */
    public int sumBitwise(int num1, int num2) {
        while (num2 != 0) {
            int carry = num1 & num2;
            num1 = num1 ^ num2;
            num2 = carry << 1;
        }
        return num1;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: 12 + 5 = 17
        int result1 = solution.sum(12, 5);
        System.out.println("Test 1: 12 + 5 = " + result1); // 17
        
        // Test case 2: -10 + 4 = -6
        int result2 = solution.sum(-10, 4);
        System.out.println("Test 2: -10 + 4 = " + result2); // -6
        
        // Test case 3: 0 + 0 = 0
        int result3 = solution.sum(0, 0);
        System.out.println("Test 3: 0 + 0 = " + result3); // 0
        
        // Test case 4: 100 + 200 = 300
        int result4 = solution.sum(100, 200);
        System.out.println("Test 4: 100 + 200 = " + result4); // 300
        
        // Test case 5: -5 + -5 = -10
        int result5 = solution.sum(-5, -5);
        System.out.println("Test 5: -5 + -5 = " + result5); // -10
        
        // Test case 6: 1 + 1 = 2
        int result6 = solution.sum(1, 1);
        System.out.println("Test 6: 1 + 1 = " + result6); // 2
        
        // Test case 7: -1 + 1 = 0
        int result7 = solution.sum(-1, 1);
        System.out.println("Test 7: -1 + 1 = " + result7); // 0
        
        // Test case 8: 999 + 1 = 1000
        int result8 = solution.sum(999, 1);
        System.out.println("Test 8: 999 + 1 = " + result8); // 1000
        
        // Test case 9: Integer.MAX_VALUE + 0 = Integer.MAX_VALUE
        int result9 = solution.sum(Integer.MAX_VALUE, 0);
        System.out.println("Test 9: MAX_VALUE + 0 = " + result9); // 2147483647
        
        // Test case 10: Integer.MIN_VALUE + 0 = Integer.MIN_VALUE
        int result10 = solution.sum(Integer.MIN_VALUE, 0);
        System.out.println("Test 10: MIN_VALUE + 0 = " + result10); // -2147483648
        
        // Test bitwise method
        System.out.println("\nTesting bitwise method:");
        System.out.println("Bitwise Test 1: 12 + 5 = " + solution.sumBitwise(12, 5)); // 17
        System.out.println("Bitwise Test 2: -10 + 4 = " + solution.sumBitwise(-10, 4)); // -6
        
        System.out.println("All test cases completed!");
    }
}

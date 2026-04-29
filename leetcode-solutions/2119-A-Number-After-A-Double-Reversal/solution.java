/**
 * LeetCode Problem: A Number After A Double Reversal
 * Problem Link: https://leetcode.com/problems/a-number-after-a-double-reversal/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-29
 * Submission Link: https://leetcode.com/problems/a-number-after-a-double-reversal/submissions/1589380620/
 */

class Solution {
    /**
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * Check if a number remains the same after reversing it twice.
     * Approach: Reverse the number and check if it equals the original.
     * 
     * @param num Input integer
     * @return boolean true if num equals double reversal, false otherwise
     */
    public boolean isSameAfterReversals(int num) {
        if (num == 0) {
            return true;
        }
        
        // If the number ends with 0, reversing will lose the leading zero
        // So double reversal won't equal the original
        if (num % 10 == 0) {
            return false;
        }
        
        // For numbers not ending with 0, double reversal equals original
        return true;
    }
    
    /**
     * Alternative solution using actual reversal
     */
    public boolean isSameAfterReversalsActual(int num) {
        int reversed1 = reverseNumber(num);
        int reversed2 = reverseNumber(reversed1);
        return num == reversed2;
    }
    
    /**
     * Helper method to reverse a number
     */
    private int reverseNumber(int num) {
        int reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: 526 -> true (526 -> 625 -> 526)
        boolean result1 = solution.isSameAfterReversals(526);
        System.out.println("Test 1: 526 -> " + result1); // true
        
        // Test case 2: 1800 -> false (1800 -> 0081 -> 81 -> 18 != 1800)
        boolean result2 = solution.isSameAfterReversals(1800);
        System.out.println("Test 2: 1800 -> " + result2); // false
        
        // Test case 3: 0 -> true (0 -> 0 -> 0)
        boolean result3 = solution.isSameAfterReversals(0);
        System.out.println("Test 3: 0 -> " + result3); // true
        
        // Test case 4: 10 -> false (10 -> 01 -> 1 -> 10 != 10)
        boolean result4 = solution.isSameAfterReversals(10);
        System.out.println("Test 4: 10 -> " + result4); // false
        
        // Test case 5: 1 -> true (1 -> 1 -> 1)
        boolean result5 = solution.isSameAfterReversals(1);
        System.out.println("Test 5: 1 -> " + result5); // true
        
        // Test case 6: 123 -> true (123 -> 321 -> 123)
        boolean result6 = solution.isSameAfterReversals(123);
        System.out.println("Test 6: 123 -> " + result6); // true
        
        // Test case 7: 120 -> false (120 -> 021 -> 21 -> 12 != 120)
        boolean result7 = solution.isSameAfterReversals(120);
        System.out.println("Test 7: 120 -> " + result7); // false
        
        // Test case 8: 100 -> false (100 -> 001 -> 1 -> 10 != 100)
        boolean result8 = solution.isSameAfterReversals(100);
        System.out.println("Test 8: 100 -> " + result8); // false
        
        // Test case 9: 999 -> true (999 -> 999 -> 999)
        boolean result9 = solution.isSameAfterReversals(999);
        System.out.println("Test 9: 999 -> " + result9); // true
        
        // Test case 10: 2000 -> false (2000 -> 0002 -> 2 -> 20 != 2000)
        boolean result10 = solution.isSameAfterReversals(2000);
        System.out.println("Test 10: 2000 -> " + result10); // false
        
        // Test actual reversal method
        System.out.println("\nTesting actual reversal method:");
        System.out.println("Actual Reversal Test 1: 526 -> " + solution.isSameAfterReversalsActual(526)); // true
        System.out.println("Actual Reversal Test 2: 1800 -> " + solution.isSameAfterReversalsActual(1800)); // false
        
        System.out.println("All test cases completed!");
    }
}

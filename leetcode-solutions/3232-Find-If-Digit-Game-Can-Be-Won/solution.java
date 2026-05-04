/**
 * LeetCode Problem: Find If Digit Game Can Be Won
 * Problem Link: https://leetcode.com/problems/find-if-digit-game-can-be-won/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-04
 * Submission Link: https://leetcode.com/problems/find-if-digit-game-can-be-won/submissions/1593537715/
 */

import java.util.Arrays;

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Check if Alice can win the digit game.
     * Alice wins if she can pick a digit that appears more than half the time.
     * Approach: Count frequency of each digit (0-9) and check if any appears > n/2 times.
     * 
     * @param num Input number as string
     * @return boolean true if Alice can win, false otherwise
     */
    public boolean canAliceWin(String num) {
        int[] frequency = new int[10];
        int length = num.length();
        
        // Count frequency of each digit
        for (int i = 0; i < length; i++) {
            frequency[num.charAt(i) - '0']++;
        }
        
        // Check if any digit appears more than half the time
        for (int count : frequency) {
            if (count > length / 2) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Alternative solution using single pass with early termination
     */
    public boolean canAliceWinOptimized(String num) {
        int[] frequency = new int[10];
        int length = num.length();
        
        for (int i = 0; i < length; i++) {
            int digit = num.charAt(i) - '0';
            frequency[digit]++;
            
            // Early check: if this digit count exceeds half, Alice can win
            if (frequency[digit] > length / 2) {
                return true;
            }
        }
        
        return false;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: "0" -> false (single digit, no majority)
        boolean result1 = solution.canAliceWin("0");
        System.out.println("Test 1: \"0\" -> " + result1); // false
        
        // Test case 2: "1234" -> false (all digits appear once, no majority)
        boolean result2 = solution.canAliceWin("1234");
        System.out.println("Test 2: \"1234\" -> " + result2); // false
        
        // Test case 3: "1111" -> true (digit 1 appears 4 times > 4/2 = 2)
        boolean result3 = solution.canAliceWin("1111");
        System.out.println("Test 3: \"1111\" -> " + result3); // true
        
        // Test case 4: "112233" -> false (each digit appears twice, no majority)
        boolean result4 = solution.canAliceWin("112233");
        System.out.println("Test 4: \"112233\" -> " + result4); // false
        
        // Test case 5: "111222" -> true (digit 1 appears 3 times > 6/2 = 3)
        boolean result5 = solution.canAliceWin("111222");
        System.out.println("Test 5: \"111222\" -> " + result5); // true
        
        // Test case 6: "123333" -> true (digit 3 appears 4 times > 6/2 = 3)
        boolean result6 = solution.canAliceWin("123333");
        System.out.println("Test 6: \"123333\" -> " + result6); // true
        
        // Test case 7: "1" -> false (single digit, no majority)
        boolean result7 = solution.canAliceWin("1");
        System.out.println("Test 7: \"1\" -> " + result7); // false
        
        // Test case 8: "11" -> true (digit 1 appears 2 times > 2/2 = 1)
        boolean result8 = solution.canAliceWin("11");
        System.out.println("Test 8: \"11\" -> " + result8); // true
        
        // Test case 9: "1234567890" -> false (all digits appear once)
        boolean result9 = solution.canAliceWin("1234567890");
        System.out.println("Test 9: \"1234567890\" -> " + result9); // false
        
        // Test case 10: "99999" -> true (digit 9 appears 5 times > 5/2 = 2)
        boolean result10 = solution.canAliceWin("99999");
        System.out.println("Test 10: \"99999\" -> " + result10); // true
        
        // Test optimized method
        System.out.println("\nTesting optimized method:");
        System.out.println("Optimized Test 1: \"1111\" -> " + solution.canAliceWinOptimized("1111")); // true
        System.out.println("Optimized Test 2: \"1234\" -> " + solution.canAliceWinOptimized("1234")); // false
        
        System.out.println("All test cases completed!");
    }
}

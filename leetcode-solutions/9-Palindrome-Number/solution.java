/**
 * LeetCode Problem: Palindrome Number
 * Problem Link: https://leetcode.com/problems/palindrome-number/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-04
 * Submission Link: https://leetcode.com/problems/palindrome-number/submissions/1557538817/
 */

class Solution {
    /**
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * Check if a number reads the same backward as forward without converting to string.
     * Approach: Revert half of the number and compare with the other half.
     * 
     * @param x Integer to check for palindrome
     * @return boolean true if palindrome, false otherwise
     */
    public boolean isPalindrome(int x) {
        // Edge cases:
        // 1. Negative numbers are not palindrome
        // 2. Numbers ending with 0 (except 0 itself) are not palindrome
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int revertedNumber = 0;
        int originalNumber = x;
        
        // Revert half of the number
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        
        // For odd length numbers, revertNumber will have one extra digit
        // So we remove the last digit from revertedNumber
        // For even length numbers, revertedNumber and x should be equal
        return x == revertedNumber || x == revertedNumber / 10;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println("121 -> " + solution.isPalindrome(121));     // true
        System.out.println("-121 -> " + solution.isPalindrome(-121));    // false
        System.out.println("10 -> " + solution.isPalindrome(10));        // false
        System.out.println("0 -> " + solution.isPalindrome(0));          // true
        System.out.println("12321 -> " + solution.isPalindrome(12321));  // true
        System.out.println("123321 -> " + solution.isPalindrome(123321)); // true
        System.out.println("12345 -> " + solution.isPalindrome(12345));  // false
        
        System.out.println("All test cases completed!");
    }
}

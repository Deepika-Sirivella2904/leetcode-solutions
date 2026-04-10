/**
 * LeetCode Problem: Valid Palindrome
 * Problem Link: https://leetcode.com/problems/valid-palindrome/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-08
 * Submission Link: http://leetcode.com/problems/valid-palindrome/submissions/1564062806/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Check if a string is a palindrome, ignoring non-alphanumeric characters and case.
     * Approach: Two-pointer technique, skipping non-alphanumeric characters.
     * 
     * @param s Input string
     * @return boolean true if palindrome, false otherwise
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Move left pointer to next alphanumeric character
            while (left < right && !isAlphaNumeric(s.charAt(left))) {
                left++;
            }
            
            // Move right pointer to previous alphanumeric character
            while (left < right && !isAlphaNumeric(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Helper method to check if character is alphanumeric
     */
    private boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println("\"A man, a plan, a canal: Panama\" -> " + solution.isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println("\"race a car\" -> " + solution.isPalindrome("race a car")); // false
        System.out.println("\" \" -> " + solution.isPalindrome(" ")); // true
        System.out.println("\"\" -> " + solution.isPalindrome("")); // true
        System.out.println("\"a\" -> " + solution.isPalindrome("a")); // true
        System.out.println("\"aba\" -> " + solution.isPalindrome("aba")); // true
        System.out.println("\"ab\" -> " + solution.isPalindrome("ab")); // false
        System.out.println("\"0P\" -> " + solution.isPalindrome("0P")); // false
        System.out.println("\"Madam\" -> " + solution.isPalindrome("Madam")); // true
        System.out.println("\"No lemon, no melon\" -> " + solution.isPalindrome("No lemon, no melon")); // true
        System.out.println("\"12321\" -> " + solution.isPalindrome("12321")); // true
        System.out.println("\"123321\" -> " + solution.isPalindrome("123321")); // true
        System.out.println("\"123ab321\" -> " + solution.isPalindrome("123ab321")); // false
        
        System.out.println("All test cases completed!");
    }
}

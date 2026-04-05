/**
 * LeetCode Problem: Length of Last Word
 * Problem Link: https://leetcode.com/problems/length-of-last-word/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-05
 * Submission Link: https://leetcode.com/problems/length-of-last-word/submissions/1558456741/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find the length of the last word in a string.
     * Approach: Start from the end and count characters until we hit a space.
     * 
     * @param s Input string
     * @return int Length of the last word
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int length = 0;
        int i = s.length() - 1;
        
        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        
        // Count the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        
        return length;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println("\"Hello World\" -> " + solution.lengthOfLastWord("Hello World"));           // 5
        System.out.println("\"   fly me   to   the moon  \" -> " + solution.lengthOfLastWord("   fly me   to   the moon  ")); // 4
        System.out.println("\"luffy is still joyboy\" -> " + solution.lengthOfLastWord("luffy is still joyboy")); // 6
        System.out.println("\"a\" -> " + solution.lengthOfLastWord("a"));                              // 1
        System.out.println("\"\" -> " + solution.lengthOfLastWord(""));                                // 0
        System.out.println("\"   \" -> " + solution.lengthOfLastWord("   "));                          // 0
        System.out.println("\"Hello\" -> " + solution.lengthOfLastWord("Hello"));                      // 5
        System.out.println("\"Hello \" -> " + solution.lengthOfLastWord("Hello "));                    // 5
        System.out.println("\" Hello\" -> " + solution.lengthOfLastWord(" Hello"));                    // 5
        
        System.out.println("All test cases completed!");
    }
}

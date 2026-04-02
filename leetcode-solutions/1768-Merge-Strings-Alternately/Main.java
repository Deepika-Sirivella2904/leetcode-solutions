/**
 * LeetCode Problem: Merge Strings Alternately
 * Problem Link: https://leetcode.com/problems/merge-strings-alternately/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-02
 * LeetCode Submission: https://leetcode.com/problems/merge-strings-alternately/submissions/1503791522/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Approach: Merge two strings by alternating characters.
     * First, iterate through the minimum length of both strings,
     * adding characters alternately. Then append the remaining
     * characters from the longer string.
     * 
     * @param word1 first input string
     * @param word2 second input string
     * @return merged string with alternating characters
     */
    public String mergeAlternately(String word1, String word2) {
        String str3 = "";
        int min = 0;
        if (word1.length() <= word2.length()) {
            min = word1.length();
        } else {
            min = word2.length();
        }
        for (int i = 0; i < min; i++) {
            str3 = str3 + word1.charAt(i);
            str3 = str3 + word2.charAt(i);
        }
        if (min < word1.length()) {
            str3 = str3 + word1.substring(min);
        } else {
            str3 = str3 + word2.substring(min);
        }
        return str3;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        String word1_1 = "abc";
        String word2_1 = "pqr";
        String result1 = solution.mergeAlternately(word1_1, word2_1);
        System.out.println("Test 1 - Input: word1=\"abc\", word2=\"pqr\"");
        System.out.println("Expected: \"apbqcr\", Got: \"" + result1 + "\"");
        System.out.println("Pass: " + result1.equals("apbqcr"));
        
        // Test case 2
        String word1_2 = "ab";
        String word2_2 = "pqrs";
        String result2 = solution.mergeAlternately(word1_2, word2_2);
        System.out.println("\nTest 2 - Input: word1=\"ab\", word2=\"pqrs\"");
        System.out.println("Expected: \"apbqrs\", Got: \"" + result2 + "\"");
        System.out.println("Pass: " + result2.equals("apbqrs"));
        
        // Test case 3
        String word1_3 = "abcd";
        String word2_3 = "pq";
        String result3 = solution.mergeAlternately(word1_3, word2_3);
        System.out.println("\nTest 3 - Input: word1=\"abcd\", word2=\"pq\"");
        System.out.println("Expected: \"apbqcd\", Got: \"" + result3 + "\"");
        System.out.println("Pass: " + result3.equals("apbqcd"));
        
        System.out.println("\nAll test cases completed!");
    }
}

/**
 * LeetCode Problem: Score of a String
 * Problem Link: https://leetcode.com/problems/score-of-a-string/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-20
 * Submission Link: https://leetcode.com/problems/score-of-a-string/submissions/1577871006/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Calculate score of a string based on consecutive character pairs.
     * Approach: Single pass counting consecutive pairs.
     * 
     * @param s Input string
     * @return int Total score
     */
    public int scoreOfString(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        
        int score = 0;
        
        for (int i = 0; i < s.length() - 1; i++) {
            score += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        
        return score;
    }
    
    /**
     * Alternative solution using enhanced for loop
     */
    public int scoreOfStringEnhanced(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        
        int score = 0;
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < chars.length - 1; i++) {
            score += Math.abs(chars[i] - chars[i + 1]);
        }
        
        return score;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: "hello" -> |h-e| + |e-l| + |l-l| + |l-o| = 7+3+0+11 = 21
        String s1 = "hello";
        int result1 = solution.scoreOfString(s1);
        System.out.println("Test 1: \"hello\" -> " + result1); // 13
        
        // Test case 2: "zaz" -> |z-a| + |a-z| = 25 + 25 = 50
        String s2 = "zaz";
        int result2 = solution.scoreOfString(s2);
        System.out.println("Test 2: \"zaz\" -> " + result2); // 50
        
        // Test case 3: "a" -> 0 (single character)
        String s3 = "a";
        int result3 = solution.scoreOfString(s3);
        System.out.println("Test 3: \"a\" -> " + result3); // 0
        
        // Test case 4: "" -> 0 (empty string)
        String s4 = "";
        int result4 = solution.scoreOfString(s4);
        System.out.println("Test 4: \"\" -> " + result4); // 0
        
        // Test case 5: "aa" -> |a-a| = 0
        String s5 = "aa";
        int result5 = solution.scoreOfString(s5);
        System.out.println("Test 5: \"aa\" -> " + result5); // 0
        
        // Test case 6: "ab" -> |a-b| = 1
        String s6 = "ab";
        int result6 = solution.scoreOfString(s6);
        System.out.println("Test 6: \"ab\" -> " + result6); // 1
        
        // Test case 7: "az" -> |a-z| = 25
        String s7 = "az";
        int result7 = solution.scoreOfString(s7);
        System.out.println("Test 7: \"az\" -> " + result7); // 25
        
        // Test case 8: "abc" -> |a-b| + |b-c| = 1 + 1 = 2
        String s8 = "abc";
        int result8 = solution.scoreOfString(s8);
        System.out.println("Test 8: \"abc\" -> " + result8); // 2
        
        // Test case 9: "abcdef" -> |a-b| + |b-c| + |c-d| + |d-e| + |e-f| = 1+1+1+1+1 = 5
        String s9 = "abcdef";
        int result9 = solution.scoreOfString(s9);
        System.out.println("Test 9: \"abcdef\" -> " + result9); // 5
        
        // Test case 10: "leetcode" -> sum of absolute differences
        String s10 = "leetcode";
        int result10 = solution.scoreOfString(s10);
        System.out.println("Test 10: \"leetcode\" -> " + result10); // 50
        
        // Test enhanced method
        System.out.println("\nTesting enhanced method:");
        System.out.println("Enhanced Test 1: \"hello\" -> " + solution.scoreOfStringEnhanced(s1)); // 13
        
        System.out.println("All test cases completed!");
    }
}

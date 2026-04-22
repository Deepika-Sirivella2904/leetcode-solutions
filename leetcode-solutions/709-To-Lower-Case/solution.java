/**
 * LeetCode Problem: To Lower Case
 * Problem Link: https://leetcode.com/problems/to-lower-case/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-22
 * Submission Link: https://leetcode.com/problems/to-lower-case/submissions/1580324299/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Convert uppercase letters to lowercase.
     * Approach: Manual character-by-character conversion.
     * 
     * @param s Input string
     * @return String Lowercase version of input string
     */
    public String toLowerCase(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // Check if character is uppercase A-Z
            if (c >= 'A' && c <= 'Z') {
                // Convert to lowercase by adding 32 (ASCII difference)
                chars[i] = (char) (c + 32);
            }
        }
        
        return new String(chars);
    }
    
    /**
     * Alternative solution using built-in method
     */
    public String toLowerCaseBuiltIn(String s) {
        return s.toLowerCase();
    }
    
    /**
     * Alternative solution using StringBuilder
     */
    public String toLowerCaseStringBuilder(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result.append((char) (c + 32));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: "Hello" -> "hello"
        String s1 = "Hello";
        String result1 = solution.toLowerCase(s1);
        System.out.println("Test 1: \"Hello\" -> \"" + result1 + "\""); // "hello"
        
        // Test case 2: "here" -> "here"
        String s2 = "here";
        String result2 = solution.toLowerCase(s2);
        System.out.println("Test 2: \"here\" -> \"" + result2 + "\""); // "here"
        
        // Test case 3: "LOVELY" -> "lovely"
        String s3 = "LOVELY";
        String result3 = solution.toLowerCase(s3);
        System.out.println("Test 3: \"LOVELY\" -> \"" + result3 + "\""); // "lovely"
        
        // Test case 4: "" -> "" (empty string)
        String s4 = "";
        String result4 = solution.toLowerCase(s4);
        System.out.println("Test 4: \"\" -> \"" + result4 + "\""); // ""
        
        // Test case 5: "a" -> "a"
        String s5 = "a";
        String result5 = solution.toLowerCase(s5);
        System.out.println("Test 5: \"a\" -> \"" + result5 + "\""); // "a"
        
        // Test case 6: "Z" -> "z"
        String s6 = "Z";
        String result6 = solution.toLowerCase(s6);
        System.out.println("Test 6: \"Z\" -> \"" + result6 + "\""); // "z"
        
        // Test case 7: "ABCdef" -> "abcdef"
        String s7 = "ABCdef";
        String result7 = solution.toLowerCase(s7);
        System.out.println("Test 7: \"ABCdef\" -> \"" + result7 + "\""); // "abcdef"
        
        // Test case 8: "123ABC" -> "123abc"
        String s8 = "123ABC";
        String result8 = solution.toLowerCase(s8);
        System.out.println("Test 8: \"123ABC\" -> \"" + result8 + "\""); // "123abc"
        
        // Test case 9: "Hello World!" -> "hello world!"
        String s9 = "Hello World!";
        String result9 = solution.toLowerCase(s9);
        System.out.println("Test 9: \"Hello World!\" -> \"" + result9 + "\""); // "hello world!"
        
        // Test case 10: "MixedCASE123!@#" -> "mixedcase123!@#"
        String s10 = "MixedCASE123!@#";
        String result10 = solution.toLowerCase(s10);
        System.out.println("Test 10: \"MixedCASE123!@#\" -> \"" + result10 + "\""); // "mixedcase123!@#"
        
        // Test built-in method
        System.out.println("\nTesting built-in method:");
        System.out.println("Built-in Test 1: \"Hello\" -> \"" + solution.toLowerCaseBuiltIn(s1) + "\""); // "hello"
        
        // Test StringBuilder method
        System.out.println("\nTesting StringBuilder method:");
        System.out.println("StringBuilder Test 1: \"Hello\" -> \"" + solution.toLowerCaseStringBuilder(s1) + "\""); // "hello"
        
        System.out.println("All test cases completed!");
    }
}

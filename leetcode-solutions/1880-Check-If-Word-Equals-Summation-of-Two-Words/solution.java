/**
 * LeetCode Problem: Check If Word Equals Summation of Two Words
 * Problem Link: https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/
 * Difficulty: Easy
 * Date Solved: 2026-04-26
 * Submission Link: https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/submissions/1584026026/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Check if the sum of two words equals a third word.
     * Each letter represents its position in alphabet (a=0, b=1, ..., z=25).
     * Approach: Convert words to numbers and compare sum.
     * 
     * @param firstWord First word
     * @param secondWord Second word
     * @param targetWord Target word
     * @return boolean true if firstWord + secondWord = targetWord
     */
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int firstValue = wordToNumber(firstWord);
        int secondValue = wordToNumber(secondWord);
        int targetValue = wordToNumber(targetWord);
        
        return firstValue + secondValue == targetValue;
    }
    
    /**
     * Helper method to convert word to number
     * Each letter is converted to its digit value (a=0, b=1, ..., z=25)
     * and concatenated to form a number
     */
    private int wordToNumber(String word) {
        int result = 0;
        
        for (int i = 0; i < word.length(); i++) {
            int digitValue = word.charAt(i) - 'a';
            result = result * 10 + digitValue;
        }
        
        return result;
    }
    
    /**
     * Alternative solution using StringBuilder
     */
    public boolean isSumEqualStringBuilder(String firstWord, String secondWord, String targetWord) {
        String firstNum = wordToString(firstWord);
        String secondNum = wordToString(secondWord);
        String targetNum = wordToString(targetWord);
        
        // Convert to integers and compare
        int firstValue = Integer.parseInt(firstNum);
        int secondValue = Integer.parseInt(secondNum);
        int targetValue = Integer.parseInt(targetNum);
        
        return firstValue + secondValue == targetValue;
    }
    
    /**
     * Helper method to convert word to string representation
     */
    private String wordToString(String word) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < word.length(); i++) {
            int digitValue = word.charAt(i) - 'a';
            sb.append(digitValue);
        }
        
        return sb.toString();
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: "ac", "bb", "aba" -> true (ac=02, bb=11, aba=010, 2+11=13 != 10)
        // Wait, let me recalculate: a=0, c=2, so ac=02=2
        // b=1, b=1, so bb=11=11
        // a=0, b=1, a=0, so aba=010=10
        // 2 + 11 = 13 != 10, so this should be false
        // Actually, the problem might be different. Let me check the expected output.
        // Based on the problem, ac=02=2, bb=11=11, aba=010=10
        // 2 + 11 = 13 != 10, so false
        boolean result1 = solution.isSumEqual("ac", "bb", "aba");
        System.out.println("Test 1: \"ac\", \"bb\", \"aba\" -> " + result1); // false
        
        // Test case 2: "aaa", "a", "aaaa" -> true (aaa=000=0, a=0, aaaa=0000=0, 0+0=0)
        boolean result2 = solution.isSumEqual("aaa", "a", "aaaa");
        System.out.println("Test 2: \"aaa\", \"a\", \"aaaa\" -> " + result2); // true
        
        // Test case 3: "abc", "def", "abc" -> false (abc=012=12, def=345=345, 12+345=357 != 12)
        boolean result3 = solution.isSumEqual("abc", "def", "abc");
        System.out.println("Test 3: \"abc\", \"def\", \"abc\" -> " + result3); // false
        
        // Test case 4: "a", "b", "c" -> true (a=0, b=1, c=2, 0+1=1 != 2, so false)
        // Actually: a=0, b=1, c=2, 0+1=1 != 2, so false
        boolean result4 = solution.isSumEqual("a", "b", "c");
        System.out.println("Test 4: \"a\", \"b\", \"c\" -> " + result4); // false
        
        // Test case 5: "a", "a", "b" -> true (a=0, a=0, b=1, 0+0=0 != 1, so false)
        // Actually: a=0, a=0, b=1, 0+0=0 != 1, so false
        boolean result5 = solution.isSumEqual("a", "a", "b");
        System.out.println("Test 5: \"a\", \"a\", \"b\" -> " + result5); // false
        
        // Test case 6: "j", "j", "b" -> true (j=9, j=9, b=1, 9+9=18 != 1, so false)
        // Actually: j=9, j=9, b=1, 9+9=18 != 1, so false
        boolean result6 = solution.isSumEqual("j", "j", "b");
        System.out.println("Test 6: \"j\", \"j\", \"b\" -> " + result6); // false
        
        // Test case 7: "z", "a", "ba" -> true (z=25, a=0, ba=10, 25+0=25 != 10, so false)
        // Actually: z=25, a=0, ba=10, 25+0=25 != 10, so false
        boolean result7 = solution.isSumEqual("z", "a", "ba");
        System.out.println("Test 7: \"z\", \"a\", \"ba\" -> " + result7); // false
        
        // Test case 8: "ab", "c", "ad" -> true (ab=01=1, c=2, ad=02=2, 1+2=3 != 2, so false)
        // Actually: ab=01=1, c=2, ad=02=2, 1+2=3 != 2, so false
        boolean result8 = solution.isSumEqual("ab", "c", "ad");
        System.out.println("Test 8: \"ab\", \"c\", \"ad\" -> " + result8); // false
        
        // Test StringBuilder method
        System.out.println("\nTesting StringBuilder method:");
        System.out.println("StringBuilder Test 1: \"aaa\", \"a\", \"aaaa\" -> " + solution.isSumEqualStringBuilder("aaa", "a", "aaaa")); // true
        
        System.out.println("All test cases completed!");
    }
}

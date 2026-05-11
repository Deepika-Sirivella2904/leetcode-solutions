/**
 * LeetCode Problem: Jewels And Stones
 * Problem Link: https://leetcode.com/problems/jewels-and-stones/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-11
 * Submission Link: https://leetcode.com/problems/jewels-and-stones/submissions/1601833487/
 */

import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * Time Complexity: O(n + m)
     * Space Complexity: O(m)
     * 
     * Count jewels in stones.
     * Jewels are characters in 'jewels', stones are characters in 'stones'.
     * Approach: Use HashSet for O(1) lookup of jewels.
     * 
     * @param jewels String of jewel characters
     * @param stones String of stone characters
     * @return int Number of stones that are jewels
     */
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
        
        // Add all jewel characters to set
        for (int i = 0; i < jewels.length(); i++) {
            jewelSet.add(jewels.charAt(i));
        }
        
        // Count jewels in stones
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewelSet.contains(stones.charAt(i))) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Alternative solution using boolean array (assuming lowercase letters)
     */
    public int numJewelsInStonesOptimized(String jewels, String stones) {
        boolean[] isJewel = new boolean[26];
        
        // Mark jewel characters
        for (int i = 0; i < jewels.length(); i++) {
            char c = jewels.charAt(i);
            if (c >= 'a' && c <= 'z') {
                isJewel[c - 'a'] = true;
            }
        }
        
        // Count jewels in stones
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            char c = stones.charAt(i);
            if (c >= 'a' && c <= 'z' && isJewel[c - 'a']) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Alternative solution using string methods
     */
    public int numJewelsInStonesStringMethods(String jewels, String stones) {
        int count = 0;
        
        for (int i = 0; i < stones.length(); i++) {
            if (jewels.indexOf(stones.charAt(i)) != -1) {
                count++;
            }
        }
        
        return count;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: jewels = "aA", stones = "aAAbbbb" -> 3
        int result1 = solution.numJewelsInStones("aA", "aAAbbbb");
        System.out.println("Test 1: jewels=\"aA\", stones=\"aAAbbbb\" -> " + result1); // 3
        
        // Test case 2: jewels = "z", stones = "ZZ" -> 0
        int result2 = solution.numJewelsInStones("z", "ZZ");
        System.out.println("Test 2: jewels=\"z\", stones=\"ZZ\" -> " + result2); // 0
        
        // Test case 3: jewels = "a", stones = "aAAbbbb" -> 3
        int result3 = solution.numJewelsInStones("a", "aAAbbbb");
        System.out.println("Test 3: jewels=\"a\", stones=\"aAAbbbb\" -> " + result3); // 3
        
        // Test case 4: jewels = "abc", stones = "aabbcccd" -> 7
        int result4 = solution.numJewelsInStones("abc", "aabbcccd");
        System.out.println("Test 4: jewels=\"abc\", stones=\"aabbcccd\" -> " + result4); // 7
        
        // Test case 5: jewels = "a", stones = "bcd" -> 0
        int result5 = solution.numJewelsInStones("a", "bcd");
        System.out.println("Test 5: jewels=\"a\", stones=\"bcd\" -> " + result5); // 0
        
        // Test case 6: jewels = "xyz", stones = "xyzxyz" -> 6
        int result6 = solution.numJewelsInStones("xyz", "xyzxyz");
        System.out.println("Test 6: jewels=\"xyz\", stones=\"xyzxyz\" -> " + result6); // 6
        
        // Test case 7: jewels = "", stones = "abc" -> 0
        int result7 = solution.numJewelsInStones("", "abc");
        System.out.println("Test 7: jewels=\"\", stones=\"abc\" -> " + result7); // 0
        
        // Test case 8: jewels = "a", stones = "" -> 0
        int result8 = solution.numJewelsInStones("a", "");
        System.out.println("Test 8: jewels=\"a\", stones=\"\" -> " + result8); // 0
        
        // Test case 9: jewels = "abcdefghijklmnopqrstuvwxyz", stones = "abcdefghijklmnopqrstuvwxyz" -> 26
        int result9 = solution.numJewelsInStones("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz");
        System.out.println("Test 9: jewels=\"abcdefghijklmnopqrstuvwxyz\", stones=\"abcdefghijklmnopqrstuvwxyz\" -> " + result9); // 26
        
        // Test case 10: jewels = "A", stones = "aAAbbbb" -> 2 (case sensitive)
        int result10 = solution.numJewelsInStones("A", "aAAbbbb");
        System.out.println("Test 10: jewels=\"A\", stones=\"aAAbbbb\" -> " + result10); // 2
        
        // Test optimized method
        System.out.println("\nTesting optimized method:");
        System.out.println("Optimized Test 1: jewels=\"a\", stones=\"aAAbbbb\" -> " + solution.numJewelsInStonesOptimized("a", "aAAbbbb")); // 3
        
        // Test string methods
        System.out.println("\nTesting string methods:");
        System.out.println("String Methods Test 1: jewels=\"a\", stones=\"aAAbbbb\" -> " + solution.numJewelsInStonesStringMethods("a", "aAAbbbb")); // 3
        
        System.out.println("All test cases completed!");
    }
}

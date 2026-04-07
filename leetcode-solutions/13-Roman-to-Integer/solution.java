/**
 * LeetCode Problem: Roman to Integer
 * Problem Link: https://leetcode.com/problems/roman-to-integer/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-07
 * Submission Link: https://leetcode.com/problems/roman-to-integer/submissions/1561292473/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Convert Roman numeral to integer.
     * Approach: Scan from left to right, subtract when smaller value precedes larger value.
     * 
     * @param s Roman numeral string
     * @return int Integer value
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int result = 0;
        int prevValue = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = getValue(s.charAt(i));
            
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            
            prevValue = currentValue;
        }
        
        return result;
    }
    
    /**
     * Helper method to get numeric value of Roman numeral character
     */
    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println("\"III\" -> " + solution.romanToInt("III"));           // 3
        System.out.println("\"IV\" -> " + solution.romanToInt("IV"));             // 4
        System.out.println("\"IX\" -> " + solution.romanToInt("IX"));             // 9
        System.out.println("\"LVIII\" -> " + solution.romanToInt("LVIII"));       // 58
        System.out.println("\"MCMXCIV\" -> " + solution.romanToInt("MCMXCIV")); // 1994
        System.out.println("\"I\" -> " + solution.romanToInt("I"));               // 1
        System.out.println("\"V\" -> " + solution.romanToInt("V"));               // 5
        System.out.println("\"X\" -> " + solution.romanToInt("X"));               // 10
        System.out.println("\"L\" -> " + solution.romanToInt("L"));               // 50
        System.out.println("\"C\" -> " + solution.romanToInt("C"));               // 100
        System.out.println("\"D\" -> " + solution.romanToInt("D"));               // 500
        System.out.println("\"M\" -> " + solution.romanToInt("M"));               // 1000
        System.out.println("\"XL\" -> " + solution.romanToInt("XL"));             // 40
        System.out.println("\"XC\" -> " + solution.romanToInt("XC"));             // 90
        System.out.println("\"CD\" -> " + solution.romanToInt("CD"));             // 400
        System.out.println("\"CM\" -> " + solution.romanToInt("CM"));             // 900
        
        System.out.println("All test cases completed!");
    }
}

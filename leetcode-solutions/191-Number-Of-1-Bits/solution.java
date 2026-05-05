/**
 * LeetCode Problem: Number Of 1 Bits
 * Problem Link: https://leetcode.com/problems/number-of-1-bits/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-05
 * Submission Link: https://leetcode.com/problems/number-of-1-bits/submissions/1595848256/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Count the number of 1 bits in the binary representation of n.
     * Approach: Use Brian Kernighan's algorithm (n & (n-1)).
     * 
     * @param n Input integer
     * @return int Number of 1 bits
     */
    public int hammingWeight(int n) {
        int count = 0;
        
        while (n != 0) {
            count++;
            n = n & (n - 1); // Remove the rightmost set bit
        }
        
        return count;
    }
    
    /**
     * Alternative solution using bit shifting
     */
    public int hammingWeightBitShift(int n) {
        int count = 0;
        
        for (int i = 0; i < 32; i++) {
            count += (n >> i) & 1;
        }
        
        return count;
    }
    
    /**
     * Alternative solution using built-in method
     */
    public int hammingWeightBuiltIn(int n) {
        return Integer.bitCount(n);
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: 11 (1011) -> 3
        int result1 = solution.hammingWeight(11);
        System.out.println("Test 1: 11 -> " + result1); // 3
        
        // Test case 2: 128 (10000000) -> 1
        int result2 = solution.hammingWeight(128);
        System.out.println("Test 2: 128 -> " + result2); // 1
        
        // Test case 3: 0 (0) -> 0
        int result3 = solution.hammingWeight(0);
        System.out.println("Test 3: 0 -> " + result3); // 0
        
        // Test case 4: 1 (1) -> 1
        int result4 = solution.hammingWeight(1);
        System.out.println("Test 4: 1 -> " + result4); // 1
        
        // Test case 5: 7 (111) -> 3
        int result5 = solution.hammingWeight(7);
        System.out.println("Test 5: 7 -> " + result5); // 3
        
        // Test case 6: 15 (1111) -> 4
        int result6 = solution.hammingWeight(15);
        System.out.println("Test 6: 15 -> " + result6); // 4
        
        // Test case 7: 31 (11111) -> 5
        int result7 = solution.hammingWeight(31);
        System.out.println("Test 7: 31 -> " + result7); // 5
        
        // Test case 8: 255 (11111111) -> 8
        int result8 = solution.hammingWeight(255);
        System.out.println("Test 8: 255 -> " + result8); // 8
        
        // Test case 9: 1023 (1111111111) -> 10
        int result9 = solution.hammingWeight(1023);
        System.out.println("Test 9: 1023 -> " + result9); // 10
        
        // Test case 10: Integer.MAX_VALUE (31 ones) -> 31
        int result10 = solution.hammingWeight(Integer.MAX_VALUE);
        System.out.println("Test 10: MAX_VALUE -> " + result10); // 31
        
        // Test bit shift method
        System.out.println("\nTesting bit shift method:");
        System.out.println("Bit Shift Test 1: 11 -> " + solution.hammingWeightBitShift(11)); // 3
        
        // Test built-in method
        System.out.println("\nTesting built-in method:");
        System.out.println("Built-in Test 1: 11 -> " + solution.hammingWeightBuiltIn(11)); // 3
        
        System.out.println("All test cases completed!");
    }
}

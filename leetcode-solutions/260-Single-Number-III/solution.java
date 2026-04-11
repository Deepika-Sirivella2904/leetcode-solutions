/**
 * LeetCode Problem: Single Number III
 * Problem Link: https://leetcode.com/problems/single-number-iii/description/
 * Difficulty: Medium
 * Date Solved: 2026-04-11
 * Submission Link: https://leetcode.com/problems/single-number-iii/submissions/1568301612/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find the two elements that appear only once while all others appear twice.
     * Approach: Bit manipulation using XOR and partitioning.
     * 
     * @param nums Array of integers
     * @return int[] Array containing the two unique numbers
     */
    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers to get xor of the two unique numbers
        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }
        
        // Step 2: Find the rightmost set bit (different bit between the two numbers)
        int rightmostSetBit = 1;
        while ((xorResult & rightmostSetBit) == 0) {
            rightmostSetBit <<= 1;
        }
        
        // Step 3: Partition numbers into two groups based on the rightmost set bit
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & rightmostSetBit) != 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        
        return new int[]{num1, num2};
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [1,2,1,3,2,5] -> [3,5] or [5,3]
        int[] nums1 = {1,2,1,3,2,5};
        int[] result1 = solution.singleNumber(nums1);
        System.out.println("Test 1: [1,2,1,3,2,5] -> [" + result1[0] + ", " + result1[1] + "]"); // [3,5] or [5,3]
        
        // Test case 2: [-1,0] -> [-1,0] or [0,-1]
        int[] nums2 = {-1,0};
        int[] result2 = solution.singleNumber(nums2);
        System.out.println("Test 2: [-1,0] -> [" + result2[0] + ", " + result2[1] + "]"); // [-1,0] or [0,-1]
        
        // Test case 3: [0,1] -> [0,1] or [1,0]
        int[] nums3 = {0,1};
        int[] result3 = solution.singleNumber(nums3);
        System.out.println("Test 3: [0,1] -> [" + result3[0] + ", " + result3[1] + "]"); // [0,1] or [1,0]
        
        // Test case 4: [2,2,1,1,3,4] -> [3,4] or [4,3]
        int[] nums4 = {2,2,1,1,3,4};
        int[] result4 = solution.singleNumber(nums4);
        System.out.println("Test 4: [2,2,1,1,3,4] -> [" + result4[0] + ", " + result4[1] + "]"); // [3,4] or [4,3]
        
        // Test case 5: [5,5,6,7,6,8,7,9] -> [8,9] or [9,8]
        int[] nums5 = {5,5,6,7,6,8,7,9};
        int[] result5 = solution.singleNumber(nums5);
        System.out.println("Test 5: [5,5,6,7,6,8,7,9] -> [" + result5[0] + ", " + result5[1] + "]"); // [8,9] or [9,8]
        
        // Test case 6: [1000000,1000000,2000000,3000000] -> [2000000,3000000] or [3000000,2000000]
        int[] nums6 = {1000000,1000000,2000000,3000000};
        int[] result6 = solution.singleNumber(nums6);
        System.out.println("Test 6: [1000000,1000000,2000000,3000000] -> [" + result6[0] + ", " + result6[1] + "]"); // [2000000,3000000] or [3000000,2000000]
        
        // Test case 7: [-2,-2,-3,-4,-3,-5,-4,-6] -> [-5,-6] or [-6,-5]
        int[] nums7 = {-2,-2,-3,-4,-3,-5,-4,-6};
        int[] result7 = solution.singleNumber(nums7);
        System.out.println("Test 7: [-2,-2,-3,-4,-3,-5,-4,-6] -> [" + result7[0] + ", " + result7[1] + "]"); // [-5,-6] or [-6,-5]
        
        // Test case 8: [1,1,2,3,2,4,4,5,5,6] -> [3,6] or [6,3]
        int[] nums8 = {1,1,2,3,2,4,4,5,5,6};
        int[] result8 = solution.singleNumber(nums8);
        System.out.println("Test 8: [1,1,2,3,2,4,4,5,5,6] -> [" + result8[0] + ", " + result8[1] + "]"); // [3,6] or [6,3]
        
        System.out.println("All test cases completed!");
    }
}

/**
 * LeetCode Problem: Single Number II
 * Problem Link: https://leetcode.com/problems/single-number-ii/description/
 * Difficulty: Medium
 * Date Solved: 2026-04-10
 * Submission Link: https://leetcode.com/problems/single-number-ii/submissions/1567041322/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find the element that appears only once while all others appear three times.
     * Approach: Bit manipulation using bitwise operators.
     * 
     * @param nums Array of integers
     * @return int Element that appears only once
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [2,2,3,2] -> 3
        int[] nums1 = {2,2,3,2};
        int result1 = solution.singleNumber(nums1);
        System.out.println("Test 1: [2,2,3,2] -> " + result1); // 3
        
        // Test case 2: [0,1,0,1,0,1,99] -> 99
        int[] nums2 = {0,1,0,1,0,1,99};
        int result2 = solution.singleNumber(nums2);
        System.out.println("Test 2: [0,1,0,1,0,1,99] -> " + result2); // 99
        
        // Test case 3: [5,5,5,10] -> 10
        int[] nums3 = {5,5,5,10};
        int result3 = solution.singleNumber(nums3);
        System.out.println("Test 3: [5,5,5,10] -> " + result3); // 10
        
        // Test case 4: [1] -> 1
        int[] nums4 = {1};
        int result4 = solution.singleNumber(nums4);
        System.out.println("Test 4: [1] -> " + result4); // 1
        
        // Test case 5: [0,0,0,1] -> 1
        int[] nums5 = {0,0,0,1};
        int result5 = solution.singleNumber(nums5);
        System.out.println("Test 5: [0,0,0,1] -> " + result5); // 1
        
        // Test case 6: [-2,-2,-2,-2,3] -> 3
        int[] nums6 = {-2,-2,-2,-2,3};
        int result6 = solution.singleNumber(nums6);
        System.out.println("Test 6: [-2,-2,-2,-2,3] -> " + result6); // 3
        
        // Test case 7: [43,16,45,89,45,-2147483648,45,2147483646,-2147483647,2147483646,-2147483648,2147483647,43,2147483646,-2147483648] -> 43
        int[] nums7 = {43,16,45,89,45,-2147483648,45,2147483646,-2147483647,2147483646,-2147483648,2147483647,43,2147483646,-2147483648};
        int result7 = solution.singleNumber(nums7);
        System.out.println("Test 7: [43,16,45,89,45,-2147483648,45,2147483646,-2147483647,2147483646,-2147483648,2147483647,43,2147483646,-2147483648] -> " + result7); // 43
        
        // Test case 8: [7,7,7,7,7,8,8,8,8,8] -> 7
        int[] nums8 = {7,7,7,7,7,8,8,8,8,8};
        int result8 = solution.singleNumber(nums8);
        System.out.println("Test 8: [7,7,7,7,7,8,8,8,8,8] -> " + result8); // 7
        
        System.out.println("All test cases completed!");
    }
}

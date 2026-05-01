/**
 * LeetCode Problem: Minimum Number Game
 * Problem Link: https://leetcode.com/problems/minimum-number-game/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-01
 * Submission Link: https://leetcode.com/problems/minimum-number-game/submissions/1590838414/
 */

import java.util.Arrays;

class Solution {
    /**
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * 
     * Play the minimum number game with Alice and Bob.
     * Alice and Bob alternate turns, each removing the smallest number.
     * Alice goes first. The result array is built by alternating their picks.
     * Approach: Sort the array and swap adjacent pairs.
     * 
     * @param nums Array of integers
     * @return int[] Result array after the game
     */
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        
        // After sorting, Alice picks first (index 0), Bob picks second (index 1)
        // But Alice adds her number first, then Bob adds his
        // So we need to swap adjacent pairs: (0,1), (2,3), (4,5), etc.
        for (int i = 0; i < nums.length - 1; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
        
        return nums;
    }
    
    /**
     * Alternative solution using new array
     */
    public int[] numberGameNewArray(int[] nums) {
        Arrays.sort(nums);
        int[] result = new int[nums.length];
        
        for (int i = 0; i < nums.length; i += 2) {
            // Alice picks nums[i], Bob picks nums[i+1]
            // Alice adds first, then Bob
            result[i] = nums[i + 1];
            result[i + 1] = nums[i];
        }
        
        return result;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [5,4,2,3] -> [3,2,5,4]
        int[] nums1 = {5,4,2,3};
        int[] result1 = solution.numberGame(nums1.clone());
        System.out.println("Test 1: [5,4,2,3] -> " + Arrays.toString(result1)); // [3,2,5,4]
        
        // Test case 2: [2,5] -> [5,2]
        int[] nums2 = {2,5};
        int[] result2 = solution.numberGame(nums2.clone());
        System.out.println("Test 2: [2,5] -> " + Arrays.toString(result2)); // [5,2]
        
        // Test case 3: [1,2,3,4,5,6] -> [2,1,4,3,6,5]
        int[] nums3 = {1,2,3,4,5,6};
        int[] result3 = solution.numberGame(nums3.clone());
        System.out.println("Test 3: [1,2,3,4,5,6] -> " + Arrays.toString(result3)); // [2,1,4,3,6,5]
        
        // Test case 4: [1] -> [1]
        int[] nums4 = {1};
        int[] result4 = solution.numberGame(nums4.clone());
        System.out.println("Test 4: [1] -> " + Arrays.toString(result4)); // [1]
        
        // Test case 5: [1,2] -> [2,1]
        int[] nums5 = {1,2};
        int[] result5 = solution.numberGame(nums5.clone());
        System.out.println("Test 5: [1,2] -> " + Arrays.toString(result5)); // [2,1]
        
        // Test case 6: [1,1,1,1] -> [1,1,1,1]
        int[] nums6 = {1,1,1,1};
        int[] result6 = solution.numberGame(nums6.clone());
        System.out.println("Test 6: [1,1,1,1] -> " + Arrays.toString(result6)); // [1,1,1,1]
        
        // Test case 7: [10,20,30,40,50,60,70,80] -> [20,10,40,30,60,50,80,70]
        int[] nums7 = {10,20,30,40,50,60,70,80};
        int[] result7 = solution.numberGame(nums7.clone());
        System.out.println("Test 7: [10,20,30,40,50,60,70,80] -> " + Arrays.toString(result7)); // [20,10,40,30,60,50,80,70]
        
        // Test new array method
        System.out.println("\nTesting new array method:");
        System.out.println("New Array Test 1: [5,4,2,3] -> " + Arrays.toString(solution.numberGameNewArray(nums1.clone()))); // [3,2,5,4]
        
        System.out.println("All test cases completed!");
    }
}

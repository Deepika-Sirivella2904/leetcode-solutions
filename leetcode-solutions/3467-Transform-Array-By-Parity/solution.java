/**
 * LeetCode Problem: Transform Array By Parity
 * Problem Link: https://leetcode.com/problems/transform-array-by-parity/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-14
 * Submission Link: https://leetcode.com/problems/transform-array-by-parity/submissions/1602822993/
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Transform array by moving all even elements to the front.
     * Approach: Separate even and odd elements, then combine.
     * 
     * @param nums Input array
     * @return int[] Transformed array with evens first
     */
    public int[] transformArray(int[] nums) {
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        
        // Separate even and odd elements
        for (int num : nums) {
            if (num % 2 == 0) {
                evens.add(num);
            } else {
                odds.add(num);
            }
        }
        
        // Combine evens followed by odds
        int[] result = new int[nums.length];
        int index = 0;
        
        for (int even : evens) {
            result[index++] = even;
        }
        
        for (int odd : odds) {
            result[index++] = odd;
        }
        
        return result;
    }
    
    /**
     * Alternative solution using two-pointer in-place
     */
    public int[] transformArrayInPlace(int[] nums) {
        int left = 0;
        
        // Move all even elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left++;
            }
        }
        
        return nums;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [3,1,2,4] -> [2,4,3,1]
        int[] nums1 = {3,1,2,4};
        int[] result1 = solution.transformArray(nums1.clone());
        System.out.println("Test 1: [3,1,2,4] -> " + java.util.Arrays.toString(result1)); // [2,4,3,1]
        
        // Test case 2: [1,3,5] -> [1,3,5] (all odd)
        int[] nums2 = {1,3,5};
        int[] result2 = solution.transformArray(nums2.clone());
        System.out.println("Test 2: [1,3,5] -> " + java.util.Arrays.toString(result2)); // [1,3,5]
        
        // Test case 3: [2,4,6] -> [2,4,6] (all even)
        int[] nums3 = {2,4,6};
        int[] result3 = solution.transformArray(nums3.clone());
        System.out.println("Test 3: [2,4,6] -> " + java.util.Arrays.toString(result3)); // [2,4,6]
        
        // Test case 4: [1] -> [1]
        int[] nums4 = {1};
        int[] result4 = solution.transformArray(nums4.clone());
        System.out.println("Test 4: [1] -> " + java.util.Arrays.toString(result4)); // [1]
        
        // Test case 5: [2] -> [2]
        int[] nums5 = {2};
        int[] result5 = solution.transformArray(nums5.clone());
        System.out.println("Test 5: [2] -> " + java.util.Arrays.toString(result5)); // [2]
        
        // Test case 6: [1,2,3,4,5,6] -> [2,4,6,1,3,5]
        int[] nums6 = {1,2,3,4,5,6};
        int[] result6 = solution.transformArray(nums6.clone());
        System.out.println("Test 6: [1,2,3,4,5,6] -> " + java.util.Arrays.toString(result6)); // [2,4,6,1,3,5]
        
        // Test case 7: [0,1,2,3] -> [0,2,1,3]
        int[] nums7 = {0,1,2,3};
        int[] result7 = solution.transformArray(nums7.clone());
        System.out.println("Test 7: [0,1,2,3] -> " + java.util.Arrays.toString(result7)); // [0,2,1,3]
        
        // Test in-place method
        System.out.println("\nTesting in-place method:");
        int[] inPlaceResult = solution.transformArrayInPlace(nums1.clone());
        System.out.println("In-Place Test 1: [3,1,2,4] -> " + java.util.Arrays.toString(inPlaceResult)); // [2,4,3,1]
        
        System.out.println("All test cases completed!");
    }
}

/**
 * LeetCode Problem: Next Greater Element I
 * Problem Link: https://leetcode.com/problems/next-greater-element-i/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-16
 * Submission Link: https://leetcode.com/problems/next-greater-element-i/submissions/1572779149/
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    /**
     * Time Complexity: O(n + m)
     * Space Complexity: O(n)
     * 
     * Find the next greater element for each element in nums1 based on nums2.
     * Approach: Monotonic stack to find next greater elements in nums2, then map to nums1.
     * 
     * @param nums1 Array of elements
     * @param nums2 Array containing nums1 elements + additional elements
     * @return int[] Array of next greater elements for each nums1 element
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Step 1: Use monotonic stack to find next greater elements for all elements in nums2
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        for (int num : nums2) {
            // While stack is not empty and current element is greater than top of stack
            while (!stack.isEmpty() && num > stack.peek()) {
                int prevNum = stack.pop();
                nextGreaterMap.put(prevNum, num); // Current num is next greater for prevNum
            }
            stack.push(num);
        }
        
        // Step 2: For remaining elements in stack, there's no next greater element
        while (!stack.isEmpty()) {
            int remainingNum = stack.pop();
            nextGreaterMap.put(remainingNum, -1);
        }
        
        // Step 3: Build result array for nums1 using the map
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }
        
        return result;
    }
    
    /**
     * Alternative solution using brute force (for understanding)
     */
    public int[] nextGreaterElementBruteForce(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        
        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            int nextGreater = -1;
            boolean found = false;
            
            // Find num1 in nums2
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == num1) {
                    found = true;
                    // Look for next greater element after this position
                    for (int k = j + 1; k < nums2.length; k++) {
                        if (nums2[k] > num1) {
                            nextGreater = nums2[k];
                            break;
                        }
                    }
                    break;
                }
            }
            
            result[i] = nextGreater;
        }
        
        return result;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums1 = [4,1,2], nums2 = [1,3,4,2] -> [-1,3,-1]
        int[] nums1_1 = {4,1,2};
        int[] nums2_1 = {1,3,4,2};
        int[] result1 = solution.nextGreaterElement(nums1_1, nums2_1);
        System.out.println("Test 1: [4,1,2], [1,3,4,2] -> [" + result1[0] + ", " + result1[1] + ", " + result1[2] + "]"); // [-1,3,-1]
        
        // Test case 2: nums1 = [2,4], nums2 = [1,2,3,4] -> [3,-1]
        int[] nums1_2 = {2,4};
        int[] nums2_2 = {1,2,3,4};
        int[] result2 = solution.nextGreaterElement(nums1_2, nums2_2);
        System.out.println("Test 2: [2,4], [1,2,3,4] -> [" + result2[0] + ", " + result2[1] + "]"); // [3,-1]
        
        // Test case 3: nums1 = [1], nums2 = [1] -> [-1]
        int[] nums1_3 = {1};
        int[] nums2_3 = {1};
        int[] result3 = solution.nextGreaterElement(nums1_3, nums2_3);
        System.out.println("Test 3: [1], [1] -> [" + result3[0] + "]"); // [-1]
        
        // Test case 4: nums1 = [1], nums2 = [1,2] -> [2]
        int[] nums1_4 = {1};
        int[] nums2_4 = {1,2};
        int[] result4 = solution.nextGreaterElement(nums1_4, nums2_4);
        System.out.println("Test 4: [1], [1,2] -> [" + result4[0] + "]"); // [2]
        
        // Test case 5: nums1 = [2], nums2 = [1,2] -> [-1]
        int[] nums1_5 = {2};
        int[] nums2_5 = {1,2};
        int[] result5 = solution.nextGreaterElement(nums1_5, nums2_5);
        System.out.println("Test 5: [2], [1,2] -> [" + result5[0] + "]"); // [-1]
        
        // Test case 6: nums1 = [3,5], nums2 = [5,1,3,2,4] -> [4,-1]
        int[] nums1_6 = {3,5};
        int[] nums2_6 = {5,1,3,2,4};
        int[] result6 = solution.nextGreaterElement(nums1_6, nums2_6);
        System.out.println("Test 6: [3,5], [5,1,3,2,4] -> [" + result6[0] + ", " + result6[1] + "]"); // [4,-1]
        
        // Test case 7: nums1 = [4,5,6], nums2 = [6,5,4,3,2,1] -> [-1,-1,-1]
        int[] nums1_7 = {4,5,6};
        int[] nums2_7 = {6,5,4,3,2,1};
        int[] result7 = solution.nextGreaterElement(nums1_7, nums2_7);
        System.out.println("Test 7: [4,5,6], [6,5,4,3,2,1] -> [" + result7[0] + ", " + result7[1] + ", " + result7[2] + "]"); // [-1,-1,-1]
        
        // Test case 8: nums1 = [1,2,3], nums2 = [1,2,3,4,5] -> [2,3,4]
        int[] nums1_8 = {1,2,3};
        int[] nums2_8 = {1,2,3,4,5};
        int[] result8 = solution.nextGreaterElement(nums1_8, nums2_8);
        System.out.println("Test 8: [1,2,3], [1,2,3,4,5] -> [" + result8[0] + ", " + result8[1] + ", " + result8[2] + "]"); // [2,3,4]
        
        // Test brute force method
        System.out.println("\nTesting brute force method:");
        int[] bruteResult = solution.nextGreaterElementBruteForce(nums1_1, nums2_1);
        System.out.println("Brute Force Test 1: [" + bruteResult[0] + ", " + bruteResult[1] + ", " + bruteResult[2] + "]"); // [-1,3,-1]
        
        System.out.println("All test cases completed!");
    }
}

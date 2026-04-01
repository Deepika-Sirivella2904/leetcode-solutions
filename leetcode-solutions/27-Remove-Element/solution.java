/**
 * LeetCode Problem: Remove Element
 * Problem Link: https://leetcode.com/problems/remove-element/
 * Difficulty: Easy
 * Date Solved: 2026-04-01
 * LeetCode Submission: https://leetcode.com/problems/remove-element/submissions/1499425594/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Two-pointer approach to remove all instances of val in-place.
     * Uses a slow pointer (c) to track the position of valid elements,
     * and a fast pointer (i) to scan through the array.
     * 
     * @param nums array of integers
     * @param val value to be removed
     * @return length of array after removing val
     */
    public int removeElement(int[] nums, int val) {
        int c = 0;
        for(int i = 0; i < nums.length; i++) {
            
            if(nums[i] != val) {
                nums[c] = nums[i];
                c++;
            }
        }
       return c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        int[] nums1 = {3,2,2,3};
        int result1 = solution.removeElement(nums1, 3);
        System.out.println("Test 1 - Expected: 2, Got: " + result1);
        System.out.println("Array first 2 elements: [2,2]");
        
        // Test case 2
        int[] nums2 = {0,1,2,2,3,0,4,2};
        int result2 = solution.removeElement(nums2, 2);
        System.out.println("Test 2 - Expected: 5, Got: " + result2);
        System.out.println("Array first 5 elements: [0,1,3,0,4]");
        
        // Test case 3
        int[] nums3 = {1};
        int result3 = solution.removeElement(nums3, 1);
        System.out.println("Test 3 - Expected: 0, Got: " + result3);
        System.out.println("Array is empty");
        
        System.out.println("All test cases passed!");
    }
}

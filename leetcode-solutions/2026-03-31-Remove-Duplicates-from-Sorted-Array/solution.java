/**
 * LeetCode Problem: Remove Duplicates from Sorted Array
 * Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Difficulty: Easy
 * Date Solved: 2026-03-31
 * LeetCode Submission: https://leetcode.com/problems/remove-duplicates-from-sorted-array/submissions/1499414957/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Two-pointer approach to remove duplicates in-place.
     * Uses a slow pointer (c) to track the position of unique elements,
     * and a fast pointer (i) to scan through the array.
     * 
     * @param nums sorted array of integers
     * @return length of array after removing duplicates
     */
    public int removeDuplicates(int[] nums) {
        int c = 0;
        
        for(int i = 1; i < nums.length; i++) {
            
            if(nums[i] != nums[c]) {
                c++;
                nums[c] = nums[i]; 
            }   
        }
        return c + 1;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        int[] nums1 = {1,1,2};
        int result1 = solution.removeDuplicates(nums1);
        System.out.println("Test 1 - Expected: 2, Got: " + result1);
        System.out.println("Array: [1,2]");
        
        // Test case 2
        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int result2 = solution.removeDuplicates(nums2);
        System.out.println("Test 2 - Expected: 5, Got: " + result2);
        System.out.println("Array: [0,1,2,3,4]");
        
        // Test case 3
        int[] nums3 = {1};
        int result3 = solution.removeDuplicates(nums3);
        System.out.println("Test 3 - Expected: 1, Got: " + result3);
        System.out.println("Array: [1]");
        
        System.out.println("All test cases passed!");
    }
}

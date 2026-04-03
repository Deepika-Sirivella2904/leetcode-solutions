/**
 * LeetCode Problem: Shuffle the Array
 * Problem Link: https://leetcode.com/problems/shuffle-the-array/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-03
 * LeetCode Submission: https://leetcode.com/problems/shuffle-the-array/submissions/1556894591/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Approach: Shuffle array by interleaving first half and second half.
     * Use two pointers - one for the first half (c) and one for the second half (n).
     * Fill the new array alternately with elements from both halves.
     * 
     * @param nums input array of length 2n
     * @param n half of the array length
     * @return shuffled array
     */
    public int[] shuffle(int[] nums, int n) {
        int[] narr = new int[2*n];
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                narr[i] = nums[c];
                c++;
            } else {
                narr[i] = nums[n];
                n++;
            }
        }
        return narr;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        int[] nums1 = {2,5,1,3,4,7};
        int n1 = 3;
        int[] result1 = solution.shuffle(nums1, n1);
        System.out.println("Test 1 - Input: nums=[2,5,1,3,4,7], n=3");
        System.out.print("Expected: [2,3,5,4,1,7], Got: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i]);
            if (i < result1.length - 1) System.out.print(",");
        }
        System.out.println("]");
        
        // Test case 2
        int[] nums2 = {1,2,3,4,4,3,2,1};
        int n2 = 4;
        int[] result2 = solution.shuffle(nums2, n2);
        System.out.println("\nTest 2 - Input: nums=[1,2,3,4,4,3,2,1], n=4");
        System.out.print("Expected: [1,4,2,3,3,2,4,1], Got: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
            if (i < result2.length - 1) System.out.print(",");
        }
        System.out.println("]");
        
        // Test case 3
        int[] nums3 = {1,1,2,2};
        int n3 = 2;
        int[] result3 = solution.shuffle(nums3, n3);
        System.out.println("\nTest 3 - Input: nums=[1,1,2,2], n=2");
        System.out.print("Expected: [1,2,1,2], Got: [");
        for (int i = 0; i < result3.length; i++) {
            System.out.print(result3[i]);
            if (i < result3.length - 1) System.out.print(",");
        }
        System.out.println("]");
        
        System.out.println("\nAll test cases completed!");
    }
}

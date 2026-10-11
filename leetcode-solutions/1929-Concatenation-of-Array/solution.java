/**
 * LeetCode Problem: Concatenation of Array
 * Problem Link: https://leetcode.com/problems/concatenation-of-array/
 * Difficulty: Easy
 * Date Solved: 2026-10-11
 * Submission Link: https://leetcode.com/problems/concatenation-of-array/submissions/1623833103/
 */

class Solution {
    public int[] getConcatenation(int[] nums) {
        int[] ans=new int[2*nums.length];
        int c=0;
        for(int i=0;i<ans.length;i++){
            if(nums.length<=i){
                ans[i]=nums[c];
                c++;
            }
            else{
                ans[i]=nums[i];
            }
        }
        return ans;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [1,2,1]
        int[] nums1 = {1, 2, 1};
        int[] result1 = solution.getConcatenation(nums1);
        System.out.print("Test 1: nums = [1,2,1] => [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i] + (i < result1.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [1,2,1,1,2,1]
        
        // Test case 2: nums = [1,3,2,1]
        int[] nums2 = {1, 3, 2, 1};
        int[] result2 = solution.getConcatenation(nums2);
        System.out.print("Test 2: nums = [1,3,2,1] => [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + (i < result2.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [1,3,2,1,1,3,2,1]
        
        System.out.println("All test cases completed!");
    }
}

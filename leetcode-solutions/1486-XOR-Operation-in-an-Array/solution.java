/**
 * LeetCode Problem: XOR Operation in an Array
 * Problem Link: https://leetcode.com/problems/xor-operation-in-an-array/
 * Difficulty: Easy
 * Date Solved: 2026-08-16
 * Submission Link: https://leetcode.com/problems/xor-operation-in-an-array/submissions/1611635181/
 */

class Solution {
    public int xorOperation(int n, int start) {
        int[] nums=new int[n];
        nums[0]=start;
        int c=0;
        for(int i=1;i<n;i++){
            nums[i]=start+2*i;
        }
        for(int i=0;i<n;i++){
            c=c^nums[i];
        }
        return c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: n = 5, start = 0
        int result1 = solution.xorOperation(5, 0);
        System.out.println("Test 1: n = 5, start = 0 => " + result1); // 8
        
        // Test case 2: n = 4, start = 3
        int result2 = solution.xorOperation(4, 3);
        System.out.println("Test 2: n = 4, start = 3 => " + result2); // 8
        
        // Test case 3: n = 1, start = 7
        int result3 = solution.xorOperation(1, 7);
        System.out.println("Test 3: n = 1, start = 7 => " + result3); // 7
        
        // Test case 4: n = 10, start = 5
        int result4 = solution.xorOperation(10, 5);
        System.out.println("Test 4: n = 10, start = 5 => " + result4);
        
        System.out.println("All test cases completed!");
    }
}

/**
 * LeetCode Problem: Three Consecutive Odds
 * Problem Link: https://leetcode.com/problems/three-consecutive-odds/description/
 * Difficulty: Easy
 * Date Solved: 2026-04-24
 * Submission Link: https://leetcode.com/problems/three-consecutive-odds/submissions/1582360307/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Check if there are three consecutive odd numbers in the array.
     * Approach: Single pass with counter.
     * 
     * @param arr Array of integers
     * @return boolean true if three consecutive odds exist, false otherwise
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }
        
        int consecutiveCount = 0;
        
        for (int num : arr) {
            if (num % 2 != 0) {
                consecutiveCount++;
                if (consecutiveCount == 3) {
                    return true;
                }
            } else {
                consecutiveCount = 0;
            }
        }
        
        return false;
    }
    
    /**
     * Alternative solution using sliding window
     */
    public boolean threeConsecutiveOddsSlidingWindow(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }
        
        for (int i = 0; i <= arr.length - 3; i++) {
            if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0) {
                return true;
            }
        }
        
        return false;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [2,6,4,1] -> false (no three consecutive odds)
        int[] arr1 = {2,6,4,1};
        boolean result1 = solution.threeConsecutiveOdds(arr1);
        System.out.println("Test 1: [2,6,4,1] -> " + result1); // false
        
        // Test case 2: [1,2,34,3,4,5,7,23,12] -> true (3,5,7 are consecutive odds)
        int[] arr2 = {1,2,34,3,4,5,7,23,12};
        boolean result2 = solution.threeConsecutiveOdds(arr2);
        System.out.println("Test 2: [1,2,34,3,4,5,7,23,12] -> " + result2); // true
        
        // Test case 3: [1,3,5] -> true (three consecutive odds)
        int[] arr3 = {1,3,5};
        boolean result3 = solution.threeConsecutiveOdds(arr3);
        System.out.println("Test 3: [1,3,5] -> " + result3); // true
        
        // Test case 4: [1,2,3] -> false (only two odds)
        int[] arr4 = {1,2,3};
        boolean result4 = solution.threeConsecutiveOdds(arr4);
        System.out.println("Test 4: [1,2,3] -> " + result4); // false
        
        // Test case 5: [] -> false (empty array)
        int[] arr5 = {};
        boolean result5 = solution.threeConsecutiveOdds(arr5);
        System.out.println("Test 5: [] -> " + result5); // false
        
        // Test case 6: [1] -> false (single element)
        int[] arr6 = {1};
        boolean result6 = solution.threeConsecutiveOdds(arr6);
        System.out.println("Test 6: [1] -> " + result6); // false
        
        // Test case 7: [1,3] -> false (two elements)
        int[] arr7 = {1,3};
        boolean result7 = solution.threeConsecutiveOdds(arr7);
        System.out.println("Test 7: [1,3] -> " + result7); // false
        
        // Test case 8: [2,4,6] -> false (all even)
        int[] arr8 = {2,4,6};
        boolean result8 = solution.threeConsecutiveOdds(arr8);
        System.out.println("Test 8: [2,4,6] -> " + result8); // false
        
        // Test case 9: [1,1,1] -> true (three consecutive odds)
        int[] arr9 = {1,1,1};
        boolean result9 = solution.threeConsecutiveOdds(arr9);
        System.out.println("Test 9: [1,1,1] -> " + result9); // true
        
        // Test case 10: [1,3,5,7,9] -> true (multiple consecutive odds)
        int[] arr10 = {1,3,5,7,9};
        boolean result10 = solution.threeConsecutiveOdds(arr10);
        System.out.println("Test 10: [1,3,5,7,9] -> " + result10); // true
        
        // Test sliding window method
        System.out.println("\nTesting sliding window method:");
        System.out.println("Sliding Window Test 1: [1,2,34,3,4,5,7,23,12] -> " + solution.threeConsecutiveOddsSlidingWindow(arr2)); // true
        
        System.out.println("All test cases completed!");
    }
}

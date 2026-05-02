/**
 * LeetCode Problem: Find Minimum Operations to Make All Elements Divisible By Three
 * Problem Link: https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-02
 * Submission Link: https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/submissions/1591792301/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Find minimum operations to make all elements divisible by 3.
     * Each operation: increment or decrement an element by 1.
     * Approach: For each element, find the minimum operations to make it divisible by 3.
     * 
     * @param nums Array of integers
     * @return int Minimum total operations
     */
    public int minimumOperations(int[] nums) {
        int totalOperations = 0;
        
        for (int num : nums) {
            int remainder = num % 3;
            
            if (remainder == 0) {
                // Already divisible by 3, no operations needed
                continue;
            } else if (remainder == 1) {
                // Need to decrement by 1 (1 operation)
                // Or increment by 2 (2 operations)
                // Minimum is 1
                totalOperations += 1;
            } else {
                // remainder == 2
                // Need to decrement by 2 (2 operations)
                // Or increment by 1 (1 operation)
                // Minimum is 1
                totalOperations += 1;
            }
        }
        
        return totalOperations;
    }
    
    /**
     * Alternative solution using modulo arithmetic
     */
    public int minimumOperationsAlternative(int[] nums) {
        int totalOperations = 0;
        
        for (int num : nums) {
            int remainder = Math.abs(num % 3);
            
            if (remainder != 0) {
                // For remainder 1 or 2, minimum operations is 1
                // (either decrement by remainder or increment by 3-remainder)
                totalOperations += 1;
            }
        }
        
        return totalOperations;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [1,2,3,4] -> 3 operations
        // 1->0 (1 op), 2->3 (1 op), 3->3 (0 op), 4->3 (1 op) = 3
        int[] nums1 = {1,2,3,4};
        int result1 = solution.minimumOperations(nums1);
        System.out.println("Test 1: [1,2,3,4] -> " + result1); // 3
        
        // Test case 2: [3,6,9] -> 0 operations (all divisible by 3)
        int[] nums2 = {3,6,9};
        int result2 = solution.minimumOperations(nums2);
        System.out.println("Test 2: [3,6,9] -> " + result2); // 0
        
        // Test case 3: [1,1,1] -> 3 operations (each needs 1 operation)
        int[] nums3 = {1,1,1};
        int result3 = solution.minimumOperations(nums3);
        System.out.println("Test 3: [1,1,1] -> " + result3); // 3
        
        // Test case 4: [2,2,2] -> 3 operations (each needs 1 operation)
        int[] nums4 = {2,2,2};
        int result4 = solution.minimumOperations(nums4);
        System.out.println("Test 4: [2,2,2] -> " + result4); // 3
        
        // Test case 5: [0] -> 0 operations (0 is divisible by 3)
        int[] nums5 = {0};
        int result5 = solution.minimumOperations(nums5);
        System.out.println("Test 5: [0] -> " + result5); // 0
        
        // Test case 6: [5] -> 1 operation (5->6 or 5->3)
        int[] nums6 = {5};
        int result6 = solution.minimumOperations(nums6);
        System.out.println("Test 6: [5] -> " + result6); // 1
        
        // Test case 7: [7] -> 1 operation (7->6 or 7->9)
        int[] nums7 = {7};
        int result7 = solution.minimumOperations(nums7);
        System.out.println("Test 7: [7] -> " + result7); // 1
        
        // Test case 8: [1,2,3,4,5,6,7,8,9] -> 6 operations
        // 1(1), 2(1), 3(0), 4(1), 5(1), 6(0), 7(1), 8(1), 9(0) = 6
        int[] nums8 = {1,2,3,4,5,6,7,8,9};
        int result8 = solution.minimumOperations(nums8);
        System.out.println("Test 8: [1,2,3,4,5,6,7,8,9] -> " + result8); // 6
        
        // Test case 9: [10,11,12] -> 2 operations
        // 10->9 (1), 11->12 (1), 12->12 (0) = 2
        int[] nums9 = {10,11,12};
        int result9 = solution.minimumOperations(nums9);
        System.out.println("Test 9: [10,11,12] -> " + result9); // 2
        
        // Test case 10: [100] -> 1 operation (100->99 or 100->102)
        int[] nums10 = {100};
        int result10 = solution.minimumOperations(nums10);
        System.out.println("Test 10: [100] -> " + result10); // 1
        
        // Test alternative method
        System.out.println("\nTesting alternative method:");
        System.out.println("Alternative Test 1: [1,2,3,4] -> " + solution.minimumOperationsAlternative(nums1)); // 3
        
        System.out.println("All test cases completed!");
    }
}

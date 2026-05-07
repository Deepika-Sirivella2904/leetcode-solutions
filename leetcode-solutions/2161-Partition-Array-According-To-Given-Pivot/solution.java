/**
 * LeetCode Problem: Partition Array According To Given Pivot
 * Problem Link: https://leetcode.com/problems/partition-array-according-to-given-pivot/description/
 * Difficulty: Medium
 * Date Solved: 2026-05-07
 * Submission Link: https://leetcode.com/problems/partition-array-according-to-given-pivot/submissions/1598191861/
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Partition array according to given pivot.
     * Elements < pivot come first, then elements = pivot, then elements > pivot.
     * Order of elements within each partition should be preserved.
     * Approach: Single pass to partition while maintaining order.
     * 
     * @param nums Input array
     * @param pivot Pivot value
     * @return int[] Partitioned array
     */
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> lessThan = new ArrayList<>();
        List<Integer> equalTo = new ArrayList<>();
        List<Integer> greaterThan = new ArrayList<>();
        
        // Partition elements while maintaining order
        for (int num : nums) {
            if (num < pivot) {
                lessThan.add(num);
            } else if (num == pivot) {
                equalTo.add(num);
            } else {
                greaterThan.add(num);
            }
        }
        
        // Combine the partitions
        int[] result = new int[nums.length];
        int index = 0;
        
        // Add elements < pivot
        for (int num : lessThan) {
            result[index++] = num;
        }
        
        // Add elements = pivot
        for (int num : equalTo) {
            result[index++] = num;
        }
        
        // Add elements > pivot
        for (int num : greaterThan) {
            result[index++] = num;
        }
        
        return result;
    }
    
    /**
     * Alternative solution using array counting
     */
    public int[] pivotArrayOptimized(int[] nums, int pivot) {
        int lessCount = 0;
        int equalCount = 0;
        int greaterCount = 0;
        
        // First pass: count elements in each partition
        for (int num : nums) {
            if (num < pivot) lessCount++;
            else if (num == pivot) equalCount++;
            else greaterCount++;
        }
        
        int[] result = new int[nums.length];
        int lessIndex = 0;
        int equalIndex = lessCount;
        int greaterIndex = lessCount + equalCount;
        
        // Second pass: place elements in correct positions
        for (int num : nums) {
            if (num < pivot) {
                result[lessIndex++] = num;
            } else if (num == pivot) {
                result[equalIndex++] = num;
            } else {
                result[greaterIndex++] = num;
            }
        }
        
        return result;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [9,12,5,10,14,3,10], pivot = 10 -> [9,5,3,10,10,12,14]
        int[] nums1 = {9,12,5,10,14,3,10};
        int[] result1 = solution.pivotArray(nums1.clone(), 10);
        System.out.println("Test 1: [9,12,5,10,14,3,10], pivot=10 -> " + java.util.Arrays.toString(result1)); // [9,5,3,10,10,12,14]
        
        // Test case 2: nums = [-3,4,3,2], pivot = 2 -> [-3,2,4,3]
        int[] nums2 = {-3,4,3,2};
        int[] result2 = solution.pivotArray(nums2.clone(), 2);
        System.out.println("Test 2: [-3,4,3,2], pivot=2 -> " + java.util.Arrays.toString(result2)); // [-3,2,4,3]
        
        // Test case 3: nums = [1], pivot = 1 -> [1]
        int[] nums3 = {1};
        int[] result3 = solution.pivotArray(nums3.clone(), 1);
        System.out.println("Test 3: [1], pivot=1 -> " + java.util.Arrays.toString(result3)); // [1]
        
        // Test case 4: nums = [5,5,5], pivot = 5 -> [5,5,5]
        int[] nums4 = {5,5,5};
        int[] result4 = solution.pivotArray(nums4.clone(), 5);
        System.out.println("Test 4: [5,5,5], pivot=5 -> " + java.util.Arrays.toString(result4)); // [5,5,5]
        
        // Test case 5: nums = [1,2,3,4,5], pivot = 3 -> [1,2,3,4,5]
        int[] nums5 = {1,2,3,4,5};
        int[] result5 = solution.pivotArray(nums5.clone(), 3);
        System.out.println("Test 5: [1,2,3,4,5], pivot=3 -> " + java.util.Arrays.toString(result5)); // [1,2,3,4,5]
        
        // Test case 6: nums = [10,20,30,40,50], pivot = 25 -> [10,20,30,40,50]
        int[] nums6 = {10,20,30,40,50};
        int[] result6 = solution.pivotArray(nums6.clone(), 25);
        System.out.println("Test 6: [10,20,30,40,50], pivot=25 -> " + java.util.Arrays.toString(result6)); // [10,20,30,40,50]
        
        // Test case 7: nums = [0,-1,2,-3,4], pivot = 0 -> [-3,-1,0,2,4]
        int[] nums7 = {0,-1,2,-3,4};
        int[] result7 = solution.pivotArray(nums7.clone(), 0);
        System.out.println("Test 7: [0,-1,2,-3,4], pivot=0 -> " + java.util.Arrays.toString(result7)); // [-3,-1,0,2,4]
        
        // Test case 8: nums = [100,50,75,25,125], pivot = 75 -> [50,25,75,100,125]
        int[] nums8 = {100,50,75,25,125};
        int[] result8 = solution.pivotArray(nums8.clone(), 75);
        System.out.println("Test 8: [100,50,75,25,125], pivot=75 -> " + java.util.Arrays.toString(result8)); // [50,25,75,100,125]
        
        // Test optimized method
        System.out.println("\nTesting optimized method:");
        int[] optimizedResult = solution.pivotArrayOptimized(nums1.clone(), 10);
        System.out.println("Optimized Test 1: [9,12,5,10,14,3,10], pivot=10 -> " + java.util.Arrays.toString(optimizedResult)); // [9,5,3,10,10,12,14]
        
        System.out.println("All test cases completed!");
    }
}

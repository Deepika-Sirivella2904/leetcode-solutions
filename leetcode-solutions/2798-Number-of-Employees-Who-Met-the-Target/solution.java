/**
 * LeetCode Problem: Number of Employees Who Met the Target
 * Problem Link: https://leetcode.com/problems/number-of-employees-who-met-the-target/
 * Difficulty: Easy
 * Date Solved: 2026-07-31
 * Submission Link: https://leetcode.com/problems/number-of-employees-who-met-the-target/submissions/1604853907/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Count the number of employees who worked at least target hours.
     * Approach: Iterate through the hours array and count employees with hours >= target.
     * 
     * @param hours Array of hours worked by each employee
     * @param target Minimum hours required to meet the target
     * @return int Number of employees who met the target
     */
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;
        for (int x : hours) {
            if (x >= target) {
                ++ans;
            }
        }
        return ans;
    }
    
    /**
     * Alternative solution using Java streams
     */
    public int numberOfEmployeesWhoMetTargetStream(int[] hours, int target) {
        return (int) java.util.Arrays.stream(hours)
                .filter(hour -> hour >= target)
                .count();
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: hours = [0,1,2,3,4], target = 2
        int[] hours1 = {0, 1, 2, 3, 4};
        int result1 = solution.numberOfEmployeesWhoMetTarget(hours1, 2);
        System.out.println("Test 1: hours = [0,1,2,3,4], target = 2 => " + result1); // 3
        
        // Test case 2: hours = [5,1,4,2,2], target = 6
        int[] hours2 = {5, 1, 4, 2, 2};
        int result2 = solution.numberOfEmployeesWhoMetTarget(hours2, 6);
        System.out.println("Test 2: hours = [5,1,4,2,2], target = 6 => " + result2); // 0
        
        // Test case 3: hours = [10,10,10], target = 10
        int[] hours3 = {10, 10, 10};
        int result3 = solution.numberOfEmployeesWhoMetTarget(hours3, 10);
        System.out.println("Test 3: hours = [10,10,10], target = 10 => " + result3); // 3
        
        // Test case 4: hours = [1,2,3,4,5], target = 0
        int[] hours4 = {1, 2, 3, 4, 5};
        int result4 = solution.numberOfEmployeesWhoMetTarget(hours4, 0);
        System.out.println("Test 4: hours = [1,2,3,4,5], target = 0 => " + result4); // 5
        
        // Test case 5: hours = [0,0,0], target = 1
        int[] hours5 = {0, 0, 0};
        int result5 = solution.numberOfEmployeesWhoMetTarget(hours5, 1);
        System.out.println("Test 5: hours = [0,0,0], target = 1 => " + result5); // 0
        
        // Test case 6: hours = [100], target = 50
        int[] hours6 = {100};
        int result6 = solution.numberOfEmployeesWhoMetTarget(hours6, 50);
        System.out.println("Test 6: hours = [100], target = 50 => " + result6); // 1
        
        // Test stream method
        System.out.println("\nTesting stream method:");
        System.out.println("Stream Test 1: " + solution.numberOfEmployeesWhoMetTargetStream(hours1, 2)); // 3
        System.out.println("Stream Test 2: " + solution.numberOfEmployeesWhoMetTargetStream(hours2, 6)); // 0
        
        System.out.println("All test cases completed!");
    }
}

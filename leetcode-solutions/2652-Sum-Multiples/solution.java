/**
 * LeetCode Problem: Sum Multiples
 * Problem Link: https://leetcode.com/problems/sum-multiples/description/
 * Difficulty: Easy
 * Date Solved: 2026-05-03
 * Submission Link: https://leetcode.com/problems/sum-multiples/submissions/1592407418/
 */

class Solution {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Sum all multiples of 3, 5, or 7 up to n.
     * Approach: Iterate from 1 to n and check divisibility.
     * 
     * @param n Upper bound
     * @return int Sum of multiples of 3, 5, or 7
     */
    public int sumOfMultiples(int n) {
        int sum = 0;
        
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                sum += i;
            }
        }
        
        return sum;
    }
    
    /**
     * Alternative solution using mathematical formula (Inclusion-Exclusion Principle)
     */
    public int sumOfMultiplesMath(int n) {
        long sum3 = sumOfDivisibleBy(n, 3);
        long sum5 = sumOfDivisibleBy(n, 5);
        long sum7 = sumOfDivisibleBy(n, 7);
        
        long sum15 = sumOfDivisibleBy(n, 15); // LCM(3,5)
        long sum21 = sumOfDivisibleBy(n, 21); // LCM(3,7)
        long sum35 = sumOfDivisibleBy(n, 35); // LCM(5,7)
        
        long sum105 = sumOfDivisibleBy(n, 105); // LCM(3,5,7)
        
        // Inclusion-Exclusion: sum3 + sum5 + sum7 - sum15 - sum21 - sum35 + sum105
        long total = sum3 + sum5 + sum7 - sum15 - sum21 - sum35 + sum105;
        
        return (int) total;
    }
    
    /**
     * Helper method to calculate sum of multiples of k up to n
     * Sum = k * (1 + 2 + ... + floor(n/k)) = k * floor(n/k) * (floor(n/k) + 1) / 2
     */
    private long sumOfDivisibleBy(int n, int k) {
        int count = n / k;
        return (long) k * count * (count + 1) / 2;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: n = 7 -> 1+3+5+6+7 = 22
        int result1 = solution.sumOfMultiples(7);
        System.out.println("Test 1: n = 7 -> " + result1); // 22
        
        // Test case 2: n = 10 -> 3+5+6+7+9+10 = 40
        int result2 = solution.sumOfMultiples(10);
        System.out.println("Test 2: n = 10 -> " + result2); // 40
        
        // Test case 3: n = 1 -> 0 (no multiples)
        int result3 = solution.sumOfMultiples(1);
        System.out.println("Test 3: n = 1 -> " + result3); // 0
        
        // Test case 4: n = 3 -> 3 = 3
        int result4 = solution.sumOfMultiples(3);
        System.out.println("Test 4: n = 3 -> " + result4); // 3
        
        // Test case 5: n = 5 -> 3+5 = 8
        int result5 = solution.sumOfMultiples(5);
        System.out.println("Test 5: n = 5 -> " + result5); // 8
        
        // Test case 6: n = 15 -> 3+5+6+7+9+10+12+14+15 = 81
        int result6 = solution.sumOfMultiples(15);
        System.out.println("Test 6: n = 15 -> " + result6); // 81
        
        // Test case 7: n = 20 -> 3+5+6+7+9+10+12+14+15+18+20 = 119
        int result7 = solution.sumOfMultiples(20);
        System.out.println("Test 7: n = 20 -> " + result7); // 119
        
        // Test case 8: n = 100 -> sum of all multiples up to 100
        int result8 = solution.sumOfMultiples(100);
        System.out.println("Test 8: n = 100 -> " + result8); // 2418
        
        // Test mathematical method
        System.out.println("\nTesting mathematical method:");
        System.out.println("Math Test 1: n = 7 -> " + solution.sumOfMultiplesMath(7)); // 22
        System.out.println("Math Test 2: n = 10 -> " + solution.sumOfMultiplesMath(10)); // 40
        System.out.println("Math Test 8: n = 100 -> " + solution.sumOfMultiplesMath(100)); // 2418
        
        System.out.println("All test cases completed!");
    }
}

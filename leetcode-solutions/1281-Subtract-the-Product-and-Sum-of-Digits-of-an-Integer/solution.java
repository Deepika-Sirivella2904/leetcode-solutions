/**
 * LeetCode Problem: Subtract the Product and Sum of Digits of an Integer
 * Problem Link: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 * Difficulty: Easy
 * Date Solved: 2026-08-19
 * Submission Link: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/submissions/1613482972/
 */

class Solution {
    public int subtractProductAndSum(int n) {
        int p=1;
        int c=0;
        while(n>0){
            int rem=n%10;
            c=c+rem;
            p=p*rem;
            n=n/10;
        }
        return p-c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: n = 234
        int result1 = solution.subtractProductAndSum(234);
        System.out.println("Test 1: n = 234 => " + result1); // 15
        
        // Test case 2: n = 4421
        int result2 = solution.subtractProductAndSum(4421);
        System.out.println("Test 2: n = 4421 => " + result2); // 21
        
        // Test case 3: n = 0
        int result3 = solution.subtractProductAndSum(0);
        System.out.println("Test 3: n = 0 => " + result3); // 1
        
        // Test case 4: n = 1
        int result4 = solution.subtractProductAndSum(1);
        System.out.println("Test 4: n = 1 => " + result4); // 0
        
        // Test case 5: n = 999
        int result5 = solution.subtractProductAndSum(999);
        System.out.println("Test 5: n = 999 => " + result5); // 729
        
        System.out.println("All test cases completed!");
    }
}

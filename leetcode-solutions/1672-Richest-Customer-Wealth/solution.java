/**
 * LeetCode Problem: Richest Customer Wealth
 * Problem Link: https://leetcode.com/problems/richest-customer-wealth/
 * Difficulty: Easy
 * Date Solved: 2026-10-13
 * Submission Link: https://leetcode.com/problems/richest-customer-wealth/submissions/1624593470/
 */

class Solution {
    public int maximumWealth(int[][] accounts) {
        int max=0;
       for(int i=0;i<accounts.length;i++){
           int sum=0;
           for(int j=0;j<accounts[i].length;j++){
               sum+=accounts[i][j];
           }
           if(max<=sum){
               max=sum;
           }
       }
       return max;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: accounts = [[1,2,3],[3,2,1]]
        int[][] accounts1 = {{1, 2, 3}, {3, 2, 1}};
        int result1 = solution.maximumWealth(accounts1);
        System.out.println("Test 1: accounts = [[1,2,3],[3,2,1]] => " + result1); // 6
        
        // Test case 2: accounts = [[1,5],[7,3],[3,5]]
        int[][] accounts2 = {{1, 5}, {7, 3}, {3, 5}};
        int result2 = solution.maximumWealth(accounts2);
        System.out.println("Test 2: accounts = [[1,5],[7,3],[3,5]] => " + result2); // 10
        
        // Test case 3: accounts = [[2,8,7],[7,1,3],[1,9,5]]
        int[][] accounts3 = {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        int result3 = solution.maximumWealth(accounts3);
        System.out.println("Test 3: accounts = [[2,8,7],[7,1,3],[1,9,5]] => " + result3); // 17
        
        System.out.println("All test cases completed!");
    }
}

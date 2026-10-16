/**
 * LeetCode Problem: Matrix Diagonal Sum
 * Problem Link: https://leetcode.com/problems/matrix-diagonal-sum/
 * Difficulty: Easy
 * Date Solved: 2026-10-16
 * Submission Link: https://leetcode.com/problems/matrix-diagonal-sum/submissions/1629447633/
 */

class Solution {
    public int diagonalSum(int[][] mat) {
        int s=0;
       int c=0;
        int k=mat.length-1;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(k>=0&&i==j){
                    s=mat[i][k]+s+mat[i][j];
                    k--;
                }
            }
        }
        int mul=mat.length/2;
        if(mat.length%2==0){
            return s;
        }
        else{
            return s-mat[mul][mul];
        }
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: mat = [[1,2,3],[4,5,6],[7,8,9]]
        int[][] mat1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int result1 = solution.diagonalSum(mat1);
        System.out.println("Test 1: mat = [[1,2,3],[4,5,6],[7,8,9]] => " + result1); // 25
        
        // Test case 2: mat = [[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1]]
        int[][] mat2 = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        int result2 = solution.diagonalSum(mat2);
        System.out.println("Test 2: mat = [[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1]] => " + result2); // 8
        
        // Test case 3: mat = [[5]]
        int[][] mat3 = {{5}};
        int result3 = solution.diagonalSum(mat3);
        System.out.println("Test 3: mat = [[5]] => " + result3); // 5
        
        System.out.println("All test cases completed!");
    }
}

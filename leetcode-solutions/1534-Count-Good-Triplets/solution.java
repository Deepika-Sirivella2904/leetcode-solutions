/**
 * LeetCode Problem: Count Good Triplets
 * Problem Link: https://leetcode.com/problems/count-good-triplets/
 * Difficulty: Easy
 * Date Solved: 2026-08-11
 * Submission Link: https://leetcode.com/problems/count-good-triplets/submissions/1606241903/
 */

class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
         int r=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j+1;k<arr.length;k++){
                    if(Math.abs(arr[i]-arr[j])<=a&&Math.abs(arr[j]-arr[k])<=b&&Math.abs(arr[i]-arr[k])<=c){
                        r++;
                    }
                    
                }
            }
        }
        return r;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
        int[] arr1 = {3, 0, 1, 1, 9, 7};
        int result1 = solution.countGoodTriplets(arr1, 7, 2, 3);
        System.out.println("Test 1: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3 => " + result1); // 4
        
        // Test case 2: arr = [1,1,2,2,3], a = 0, b = 0, c = 1
        int[] arr2 = {1, 1, 2, 2, 3};
        int result2 = solution.countGoodTriplets(arr2, 0, 0, 1);
        System.out.println("Test 2: arr = [1,1,2,2,3], a = 0, b = 0, c = 1 => " + result2); // 0
        
        System.out.println("All test cases completed!");
    }
}

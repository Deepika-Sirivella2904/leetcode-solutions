/**
 * LeetCode Problem: Split a String in Balanced Strings
 * Problem Link: https://leetcode.com/problems/split-a-string-in-balanced-strings/
 * Difficulty: Easy
 * Date Solved: 2026-08-17
 * Submission Link: https://leetcode.com/problems/split-a-string-in-balanced-strings/submissions/1612558958/
 */

class Solution {
    public int balancedStringSplit(String s) {
        int r=0;
        int c=0;
        for(int i=0;i<s.length();i++){
            
            if(s.charAt(i)=='R'){
                r++;
            }
            else{
                r--;
            }
            if(r==0){
                c++;
            }
        }
        return c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: s = "RLRRLLRLRL"
        int result1 = solution.balancedStringSplit("RLRRLLRLRL");
        System.out.println("Test 1: s = \"RLRRLLRLRL\" => " + result1); // 4
        
        // Test case 2: s = "RLRRRLLRLL"
        int result2 = solution.balancedStringSplit("RLRRRLLRLL");
        System.out.println("Test 2: s = \"RLRRRLLRLL\" => " + result2); // 3
        
        // Test case 3: s = "LLLLRRRR"
        int result3 = solution.balancedStringSplit("LLLLRRRR");
        System.out.println("Test 3: s = \"LLLLRRRR\" => " + result3); // 1
        
        System.out.println("All test cases completed!");
    }
}

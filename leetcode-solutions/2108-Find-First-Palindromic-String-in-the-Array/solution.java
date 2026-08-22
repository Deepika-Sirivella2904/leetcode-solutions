/**
 * LeetCode Problem: Find First Palindromic String in the Array
 * Problem Link: https://leetcode.com/problems/find-first-palindromic-string-in-the-array/
 * Difficulty: Easy
 * Date Solved: 2026-08-22
 * Submission Link: https://leetcode.com/problems/find-first-palindromic-string-in-the-array/submissions/1617834520/
 */

class Solution {
    public String firstPalindrome(String[] words) {
        String re="";
        for(int i=0;i<words.length;i++){
           String str=words[i];
           String s="";
           for(int j=0;j<str.length();j++){
               s=str.charAt(j)+s;
           }
           if(s.equals(str)){
               re=str;
               break;
           }
       }
       return re;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: words = ["abc","car","ada","racecar","cool"]
        String[] words1 = {"abc", "car", "ada", "racecar", "cool"};
        String result1 = solution.firstPalindrome(words1);
        System.out.println("Test 1: words = [\"abc\",\"car\",\"ada\",\"racecar\",\"cool\"] => " + result1); // "ada"
        
        // Test case 2: words = ["notapalindrome","racecar"]
        String[] words2 = {"notapalindrome", "racecar"};
        String result2 = solution.firstPalindrome(words2);
        System.out.println("Test 2: words = [\"notapalindrome\",\"racecar\"] => " + result2); // "racecar"
        
        // Test case 3: words = ["def","ghi"]
        String[] words3 = {"def", "ghi"};
        String result3 = solution.firstPalindrome(words3);
        System.out.println("Test 3: words = [\"def\",\"ghi\"] => " + result3); // ""
        
        System.out.println("All test cases completed!");
    }
}

/**
 * LeetCode Problem: Reverse Prefix of Word
 * Problem Link: https://leetcode.com/problems/reverse-prefix-of-word/
 * Difficulty: Easy
 * Date Solved: 2026-08-22
 * Submission Link: https://leetcode.com/problems/reverse-prefix-of-word/submissions/1615962523/
 */

class Solution {
    public String reversePrefix(String word, char ch) {
        int c=0;
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==ch){
                break;
            }
            c++;
        }
        if(c==word.length()){
            return word;
        }
        else{
            String str="";
            for(int i=0;i<word.length();i++){
                if(c>=i){
                    str=word.charAt(i)+str;
                }
                else{
                    str=str+word.charAt(i);
                }
            }
            return str;
        }
        
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: word = "abcdefd", ch = 'd'
        String result1 = solution.reversePrefix("abcdefd", 'd');
        System.out.println("Test 1: word = \"abcdefd\", ch = 'd' => " + result1); // "dcbaefd"
        
        // Test case 2: word = "xyxzxe", ch = 'z'
        String result2 = solution.reversePrefix("xyxzxe", 'z');
        System.out.println("Test 2: word = \"xyxzxe\", ch = 'z' => " + result2); // "zxyxxe"
        
        // Test case 3: word = "abcd", ch = 'z'
        String result3 = solution.reversePrefix("abcd", 'z');
        System.out.println("Test 3: word = \"abcd\", ch = 'z' => " + result3); // "abcd"
        
        System.out.println("All test cases completed!");
    }
}

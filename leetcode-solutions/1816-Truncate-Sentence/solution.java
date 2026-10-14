/**
 * LeetCode Problem: Truncate Sentence
 * Problem Link: https://leetcode.com/problems/truncate-sentence/
 * Difficulty: Easy
 * Date Solved: 2026-10-14
 * Submission Link: https://leetcode.com/problems/truncate-sentence/submissions/1628735308/
 */

class Solution {
    public String truncateSentence(String s, int k) {
        String rev="";
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch==' '&&k>0){
                k--;
                if(k==0){
                    break;
                }
                else{
                    rev=rev+ch;
                }
            }
            else if(ch!=' '){
                rev=rev+ch;
            }
        }
        return rev;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: s = "Hello how are you Contestant", k = 4
        String result1 = solution.truncateSentence("Hello how are you Contestant", 4);
        System.out.println("Test 1: s = \"Hello how are you Contestant\", k = 4 => " + result1); // "Hello how are you"
        
        // Test case 2: s = "What is the solution to this problem", k = 4
        String result2 = solution.truncateSentence("What is the solution to this problem", 4);
        System.out.println("Test 2: s = \"What is the solution to this problem\", k = 4 => " + result2); // "What is the solution"
        
        // Test case 3: s = "chopper is not a tanuki", k = 5
        String result3 = solution.truncateSentence("chopper is not a tanuki", 5);
        System.out.println("Test 3: s = \"chopper is not a tanuki\", k = 5 => " + result3); // "chopper is not a tanuki"
        
        System.out.println("All test cases completed!");
    }
}

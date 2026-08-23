/**
 * LeetCode Problem: Check if the Sentence is Pangram
 * Problem Link: https://leetcode.com/problems/check-if-the-sentence-is-pangram/
 * Difficulty: Easy
 * Date Solved: 2026-08-23
 * Submission Link: https://leetcode.com/problems/check-if-the-sentence-is-pangram/submissions/1618132837/
 */

class Solution {
    public boolean checkIfPangram(String sentence) {
        int[] letters = new int[26];
        boolean b=false;
        if(sentence.length()<26){
            return (b);
        }
        else{
            for(int i=0;i<sentence.length();i++){
                char ch=sentence.charAt(i);
                letters[ch-'a']=1;
            }
            int c=0;
            for(int i=0;i<26;i++){
                if(letters[i]==1){
                    c++;
                }
            }
            if(c==26){
                b=true;
            }
           return (b);
        }
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: sentence = "thequickbrownfoxjumpsoverthelazydog"
        boolean result1 = solution.checkIfPangram("thequickbrownfoxjumpsoverthelazydog");
        System.out.println("Test 1: sentence = \"thequickbrownfoxjumpsoverthelazydog\" => " + result1); // true
        
        // Test case 2: sentence = "leetcode"
        boolean result2 = solution.checkIfPangram("leetcode");
        System.out.println("Test 2: sentence = \"leetcode\" => " + result2); // false
        
        System.out.println("All test cases completed!");
    }
}

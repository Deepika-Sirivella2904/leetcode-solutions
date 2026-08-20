/**
 * LeetCode Problem: Maximum Number of Words found in Sentences
 * Problem Link: https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/
 * Difficulty: Easy
 * Date Solved: 2026-08-20
 * Submission Link: https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/submissions/1614224731/
 */

class Solution {
    public int mostWordsFound(String[] sentences) {
        int[] n=new int[sentences.length];
        for(int i=0;i<sentences.length;i++){
            int c=0;
            for(int j=0;j<sentences[i].length();j++){
                if(sentences[i].charAt(j)==' '){
                    c++;
                }
            }
            n[i]=c;
        }
        int max=n[0];
        for(int i=1;i<n.length;i++){
            if(max<n[i]){
                max=n[i];
            }
        }
        return max+1;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: sentences = ["alice and bob love leetcode", "i think so too", "this is great thanks very much"]
        String[] sentences1 = {"alice and bob love leetcode", "i think so too", "this is great thanks very much"};
        int result1 = solution.mostWordsFound(sentences1);
        System.out.println("Test 1: sentences = [\"alice and bob love leetcode\", \"i think so too\", \"this is great thanks very much\"] => " + result1); // 6
        
        // Test case 2: sentences = ["please wait", "continue to fight", "continue to win"]
        String[] sentences2 = {"please wait", "continue to fight", "continue to win"};
        int result2 = solution.mostWordsFound(sentences2);
        System.out.println("Test 2: sentences = [\"please wait\", \"continue to fight\", \"continue to win\"] => " + result2); // 3
        
        System.out.println("All test cases completed!");
    }
}

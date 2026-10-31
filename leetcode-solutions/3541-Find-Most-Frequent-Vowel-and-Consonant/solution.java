/**
 * Problem: 3541. Find Most Frequent Vowel and Consonant
 * Link: https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/submissions/1650853432/
 */

class Solution {
    public int maxFreqSum(String s) {
        int[] alp=new int[26];
        int maxvo=0;
        int maxco=0;
        for(int i=0;i<s.length();i++){
            int n=s.charAt(i)-'a';
            alp[n]=alp[n]+1;
            char ch=s.charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
                if(alp[n]>maxvo){
                    maxvo=alp[n];
                }
            }
            else{
                if(alp[n]>maxco){
                    maxco=alp[n];
                }
            }
        }
        return maxco+maxvo;
    }
}

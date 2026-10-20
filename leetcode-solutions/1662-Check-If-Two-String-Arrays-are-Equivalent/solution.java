/**
 * Problem: 1662. Check If Two String Arrays are Equivalent
 * Link: https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/submissions/1644322442/
 */

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        boolean b=false;
        String str1="";
        for(int i=0;i<word1.length;i++){
            String w=word1[i];
            str1=str1+w;
        }
        String str2="";
        for(int i=0;i<word2.length;i++){
            String w2=word2[i];
            str2=str2+w2;
        }
        if(str1.equals(str2)){
            b=true;
        }
        else{
            b=false;
        }
        return b;
    }
}

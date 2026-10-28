/**
 * Problem: 1528. Shuffle String
 * Link: https://leetcode.com/problems/shuffle-string/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/shuffle-string/submissions/1649103431/
 */

class Solution {
    public String restoreString(String s, int[] indices) {
        String r="";
        for(int i=0;i<s.length();i++){
            for(int j=0;j<s.length();j++){
                if(indices[j]==i){
                    char ch=s.charAt(j);
                    r=r+ch;
                }
            }

        }
        return r;
    }
}

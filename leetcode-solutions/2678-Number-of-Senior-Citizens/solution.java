/**
 * Problem: 2678. Number of Senior Citizens
 * Link: https://leetcode.com/problems/number-of-senior-citizens/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/number-of-senior-citizens/submissions/1645208925/
 */

class Solution {
    public int countSeniors(String[] details) {
        int c=0;
        for(int i=0;i<details.length;i++){
            String w=details[i];
            String str=" ";
            for(int j=0;j<2;j++){
                char ch=w.charAt(11+j);
                str=str+ch;
            }
            if(str.charAt(1)=='6'){
                if(str.charAt(2)=='0'){
                    continue;
                }
                else{
                    c++;
                }
            }
            else if(str.charAt(1)=='7'||str.charAt(1)=='8'||str.charAt(1)=='9'){
                c++;
            }
        }
        return c;
    }
}

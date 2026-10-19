/**
 * Problem: 1051. Height Checker
 * Link: https://leetcode.com/problems/height-checker/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/height-checker/submissions/1643299396/
 */

class Solution {
    public int heightChecker(int[] heights) {
        int[] t=new int[heights.length];
        for(int i=0;i<t.length;i++){
            t[i]=heights[i];
        }
        for(int i=0;i<heights.length;i++){
            for(int j=i+1;j<heights.length;j++){
                if(heights[i]>heights[j]){
                    int tem=heights[i];
                    heights[i]=heights[j];
                    heights[j]=tem;
                }
            }
        }
        int c=0;
        for(int i=0;i<t.length;i++){
            if(heights[i]!=t[i]){
                c++;
            }
        }
        return c;
    }
}

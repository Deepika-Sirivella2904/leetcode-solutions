/**
 * Problem: 832. Flipping an Image
 * Link: https://leetcode.com/problems/flipping-an-image/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/flipping-an-image/submissions/1649969113/
 */

class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int[][] r=new int[image.length][image.length];
        for(int i=0;i<image.length;i++){
            int c=0;
            for(int j=image[i].length-1;j>=0;j--){
                r[i][j]=image[i][c];
                c++;
            }
        }
        for(int i=0;i<image.length;i++){
            for(int j=0;j<image[i].length;j++){
                if(r[i][j]==0){
                    r[i][j]=1;
                }
                else{
                    r[i][j]=0;
                }
            }
        }
        return r;
    }
}

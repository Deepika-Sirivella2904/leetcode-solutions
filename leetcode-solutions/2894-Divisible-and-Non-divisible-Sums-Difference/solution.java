/**
 * Problem: 2894. Divisible and Non-divisible Sums Difference
 * Link: https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/submissions/1645835931/
 */

class Solution {
    public int differenceOfSums(int n, int m) {
        int div=0;
        int ndiv=0;
        for(int i=1;i<=n;i++){
            if(i%m==0){
                div+=i;
            }
            else{
                ndiv+=i;
            }
        }
        return ndiv-div;
    }
}

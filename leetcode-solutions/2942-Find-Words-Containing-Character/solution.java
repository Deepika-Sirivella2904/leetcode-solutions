/**
 * Problem: 2942. Find Words Containing Character
 * Link: https://leetcode.com/problems/find-words-containing-character/description/
 * Difficulty: Easy
 * Submission: https://leetcode.com/problems/find-words-containing-character/submissions/1646975125/
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> r=new ArrayList<>();
        for(int i=0;i<words.length;i++){
            String w=words[i];
            for(int j=0;j<w.length();j++){
                char ch=w.charAt(j);
                if(ch==x){
                    r.add(i);
                    break;
                }
            }
        }
        return r;
    }
}

package com.liang.leetcode.window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 字母异位词
public class Lc438 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
        String s1 = "abab", p1 = "ab";
        System.out.println(findAnagrams(s1, p1));
    }
    private static List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        if(slen<plen) return null;
        int[] sc = new int[26];
        int[] pc = new int[26];
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < plen; i++){
            sc[s.charAt(i) - 'a']++;
            pc[p.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sc, pc)){
            ans.add(0);
        }

        for(int i = 0; i < slen-plen; i++){
            sc[s.charAt(i) - 'a']--;
            sc[s.charAt(i+plen) - 'a']++;
            if(Arrays.equals(sc, pc)){
                ans.add(i+1);
            }
        }

        return ans;
    }
}

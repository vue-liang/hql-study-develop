package com.liang.leetcode.window;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author liang
 * @create 2025-12-14 21:07
 * @description 76. 最小覆盖子串
 */
public class Lc76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));

    }

    public static String minWindow(String s, String t) {
        if (s.equals(t)) return t;
        Map<Character, Integer> tCount = new HashMap<>(t.length());
        char[] tCharArray = t.toCharArray();
        int less = 0;
        for (char tc : tCharArray) {
            if(tCount.get(tc)==null){
                less++;
            }
            tCount.put(tc, tCount.getOrDefault(tc, 0) + 1);
        }
        int m = s.length();
        int ansLeft = -1;
        int ansRight = m;
        int left = 0;
        Map<Character, Integer> sCount = new HashMap<>();
        for (int right = 0; right < m; right++) {
            char sc = s.charAt(right);
            sCount.put(sc, sCount.getOrDefault(sc, 0) + 1);
            if(Objects.equals(sCount.get(sc), tCount.get(sc))){
                less--;
            }
            while (less==0) {
                if (right - left < ansRight - ansLeft) { // 找到更短的子串
                    ansLeft = left; // 记录此时的左右端点
                    ansRight = right;
                }
                char c = s.charAt(left);
                if(Objects.equals(sCount.get(c), tCount.get(c))){
                    less++;
                }
                sCount.put(c, sCount.get(c) - 1);
                left++;
            }
        }
        return ansLeft < 0 ? "" : s.substring(ansLeft, ansRight + 1);
    }

    private static boolean isCover(Map<Character, Integer> t, Map<Character, Integer> s) {
        Set<Character> keys = t.keySet();
        for (Character key : keys) {
            if (s.get(key) == null || s.get(key) < t.get(key)) {
                return false;
            }
        }
        return true;
    }
}

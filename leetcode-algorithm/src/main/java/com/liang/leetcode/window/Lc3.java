package com.liang.leetcode.window;

import java.util.HashMap;

// 无重复最大子串
public class Lc3 {
    public static void main(String[] args) {
        String s1 = "abba";
        String s2 = "abcabcbb";
        String s3 = "bbbbbbbb";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));
    }
    private static int lengthOfLongestSubstring(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int begin = 0;
        for(int end = 0; end < s.length(); end++){
            char c = s.charAt(end);
            // 出现重复
            if(map.containsKey(c)){
                // abba
                begin = Math.max(begin, map.get(c) + 1);
            }
            // k:字符 v:最靠右的字符索引
            map.put(c, end);
            max = Math.max(max, end - begin + 1);
        }
        return max;
    }
}

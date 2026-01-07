package com.liang.leetcode.window;

import java.util.HashMap;

// 无重复最大子串
public class Lc3 {
    public static void main(String[] args) {
        String s1 = "abba"; // 2
        String s2 = "abcabcbb";// 3
        String s3 = "bbbbbbbb";// 1
        System.out.println(lengthOfLongestSubstring1(s1));
        System.out.println(lengthOfLongestSubstring1(s2));
        System.out.println(lengthOfLongestSubstring1(s3));
    }

    private static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int begin = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            // 出现重复
            if (map.containsKey(c)) {
                // abba
                begin = Math.max(begin, map.get(c) + 1);
            }
            // k:字符 v:最靠右的字符索引
            map.put(c, end);
            max = Math.max(max, end - begin + 1);
        }
        return max;
    }

    public static int lengthOfLongestSubstring1(String s) {
        int ans = 0;
        HashMap<Character, Integer> count = new HashMap<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count.put(c, count.getOrDefault(c, 0) + 1);
            while (count.get(c) > 1) {
                char c1 = s.charAt(left);
                count.put(c1, count.get(c1) - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

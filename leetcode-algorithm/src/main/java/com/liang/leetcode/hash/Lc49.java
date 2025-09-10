package com.liang.leetcode.hash;

import java.util.*;
import java.util.stream.Collectors;

// 字母异位词分类
public class Lc49 {
    public static void main(String[] args) {
        String[] strs={"eat", "tea", "tan", "ate", "nat", "bat"};
//        // 测试api
//        Map<String, List<String>> map = new HashMap<>();
//        map.put("eat", Arrays.asList("eat", "tea", "ate"));
//        map.put("tan", Arrays.asList("tan", "nat"));
//        map.put("bat", List.of("bat"));
//        Collection<List<String>> values = map.values();
//        System.out.println(values);
//
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagrams1(strs));
    }
    // 字母排序 ? 检测 插入
    private static List<List<String>> groupAnagrams(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = Arrays.toString(chars);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s,list);
            }
        }
        return new ArrayList<>(map.values());
    }
    private static List<List<String>> groupAnagrams1(String[] strs){
        Collection<List<String>> values = Arrays.stream(strs).collect(Collectors.groupingBy(
                str -> {
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                }
        )).values();
        return new ArrayList<>(values);
    }
}

package com.liang.leetcode.hash;

import java.util.HashSet;

public class Lc128 {
    public static void main(String[] args) {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        int[] nums1 = {0,3,7,2,5,9,4,6,0,1};
        System.out.println(longestConsecutive(nums));
        System.out.println(longestConsecutive(nums1));
    }
    private static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }
        int count;
        int max = 0;
        for(Integer s : set){
            count = 1;
            if(set.contains(s-1)){
                continue;
            }
            while(set.contains(++s)){
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

package com.liang.leetcode.greedy;

// 45. 跳跃游戏 II
public class Lc45 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        int len = nums.length;
        int max = 0, cur = 0, count = 0;
        for (int i = 0; i < len-1; i++) {
            max = Math.max(max, nums[i] + i);
            if (cur == i) {
                cur = max;
                count++;
            }
        }
        return count;
    }
}

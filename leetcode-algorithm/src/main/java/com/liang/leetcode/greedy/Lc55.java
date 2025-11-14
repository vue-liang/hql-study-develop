package com.liang.leetcode.greedy;

// 55. 跳跃游戏
public class Lc55 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums1 = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
        System.out.println(canJump(nums1));
    }

    public static boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(nums[i] + i, max);
        }
        return true;
    }
}

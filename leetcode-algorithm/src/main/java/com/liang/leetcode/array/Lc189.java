package com.liang.leetcode.array;

import java.util.Arrays;

// 189. 轮转数组
public class Lc189 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        rotate1(nums1, 2);
        System.out.println(Arrays.toString(nums1));
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || k == nums.length || len <= 1) {
            return;
        }
        int[] t = new int[len];
        System.arraycopy(nums, 0, t, 0, len);
        if (k > len) {
            k = k % len;
        }
        for (int i = 0; i < len; i++) {
            if (i + k < len) {
                nums[i + k] = t[i];
            } else {
                nums[i + k - len] = t[i];
            }
        }
    }
    public static void rotate1(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || k == nums.length || len <= 1) {
            return;
        }
        int[] t = new int[len];
        System.arraycopy(nums, 0, t, 0, len);
        if (k > len) {
            k = k % len;
        }
        for (int i = 0; i < len; i++) {
            if(i>=k){
                nums[i] = t[i-k];
            }else {
                nums[i] = t[len-k+i];
            }
        }
    }
}

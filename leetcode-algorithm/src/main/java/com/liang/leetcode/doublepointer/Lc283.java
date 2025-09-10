package com.liang.leetcode.doublepointer;

import java.util.Arrays;

public class Lc283 {
    public static void main(String[] args) {
        int[] nums = {1,2,0,4,0,9};
//        moveZeroes(nums);
        moveZeroes1(nums);
        System.out.println(Arrays.toString(nums));
    }
    private static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }
    private static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }
}

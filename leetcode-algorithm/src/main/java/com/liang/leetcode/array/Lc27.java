package com.liang.leetcode.array;

import java.util.Arrays;

// 与移动0类似
public class Lc27 {
    public static void main(String[] args) {
        Lc27 lc27 = new Lc27();
        int[] nums = {0,1,2,2,3,0,4,2};
        System.out.println(lc27.removeElement(nums,2));
        System.out.println(Arrays.toString(nums));
    }
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j]!=val){
                int t = nums[j];
                nums[j] = nums[i];
                nums[i++] = t;
            }
        }
        return i;
    }
}

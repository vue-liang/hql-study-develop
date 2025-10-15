package com.liang.leetcode.window;

// 和比K大的最小数组
public class Lc209 {
    public static void main(String[] args) {
        int[] arr = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7,arr));
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                min = Math.min(min,i-j+1);
                sum -= nums[j++];
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }
}

package com.liang.leetcode.array;

// 53. 最大子数组和 重点：前面的数字之和是否大于0
public class Lc53 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums1 = {5,4,-1,7,8};
        int[] nums2 = {-2,-1};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray(nums1));
        System.out.println(maxSubArray(nums2));
    }
    public static int maxSubArray(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length]; // dp[i] 表示以 nums[i] 结尾的连续子数组的最大和
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public static int maxSubArray1(int[] nums) {
        int pre = 0;
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
        }
        return res;
    }
}

package com.liang.leetcode.array;

import java.util.HashMap;

// 和为k的子数组
public class Lc560 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 9;
        System.out.println(subarraySum(nums, k));
    }
    private static int subarraySum(int[] nums, int k){
        int len = nums.length;
        if (nums.length == 1) {
            return nums[0] == k ? 1 : 0;
        }
        int[] sum = new int[len];
        sum[0] = nums[0];
        // sum[i] 代表前缀和 从 0 + 到 i
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int ans = 0;
        // sum[i]-sum[j] = k map k:sum[i] v:出现次数 sum[i]-k==sum[j]
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < len; i++) {
            if(map.containsKey(sum[i]-k)){
                ans += map.get(sum[i]-k);
            }
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return ans;
    }
}

package com.liang.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
// 两数之和
public class Lc1 {
    public static void main(String[] args) {
       int[] nums = {1,2,3,4,5,6,7,8,9};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
        System.out.println(Arrays.toString(twoSum(nums, 7)));
    }
    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // key:存储值 v:存储索引 返回的值
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }else{
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }
}

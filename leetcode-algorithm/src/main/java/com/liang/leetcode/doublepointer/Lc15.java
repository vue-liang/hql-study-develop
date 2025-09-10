package com.liang.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 三数之和
public class Lc15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
    private static List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        int l,r,sum;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i<nums.length-2; i++){
            if(nums[i]>0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            l = i+1;
            r = nums.length-1;
            while (l<r){
                sum = nums[i] + nums[l] + nums[r];
                if(sum==0){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l<r && nums[l]==nums[l+1]) l++; // 去重
                    while (l<r && nums[r]==nums[r-1]) r--; // 去重
                    l++;
                    r--;
                }
                else if(sum>0){
                    r--;
                }
                else{
                    l++;
                }
            }
        }
        return res;
    }
}

package com.liang.leetcode.doublepointer;

// 接雨水
public class Lc42 {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height1 = {4,2,0,3,2,5};
        System.out.println(trap(height));
        System.out.println(trap(height1));
    }
    private static int trap(int[] height) {
        int max = 0;
        int sum = 0;
        for (int j : height) {
            if (j > max)
                max = j;
            sum += j;
        }
        int count=0;
        int l,r;
        for (int i = 1; i <= max; i++) {
            l = 0;
            r = height.length - 1;
            while (l<r&&height[l]<i) l++;
            while (l<r&&height[r]<i) r--;
            count+=r-l+1;
        }
        return count-sum;
    }

}

package com.liang.leetcode.doublepointer;
// 最多水的容器
public class Lc11 {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
    private static int maxArea(int[] height){
        int l = 0;
        int r = height.length-1;
        int max = 0;
        while(l<r){
            max = height[l]>height[r]?
                    Math.max(max,(r-l)*height[r--]):
                    Math.max(max,(r-l)*height[l++]);
        }
        return max;
    }
}

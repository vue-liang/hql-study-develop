package com.liang.leetcode.array;

import java.util.Arrays;

// 二分查找，并找出相应的插入位置
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-2, 1, 5, 6, 8, 9, 11, 11, 23};
//        System.out.println(binarySearch(arr, 12));
//        System.out.println(getLeftBorder(arr, 11) + 1);
//        System.out.println(getRightBorder(arr, 11) - 1);
        int[] arr1 = {5, 7, 7, 8, 8, 10};
//        System.out.println(getLeftBorder(arr1, 8) + 1);
//        System.out.println(getRightBorder(arr1, 8) - 1);
//        System.out.println(Arrays.toString(searchRange(arr, 11)));
//        System.out.println(Arrays.toString(searchRange(arr1, 8)));
//        System.out.println("平方根取整" + mySqrt(9));
//        System.out.println("平方根取整" + mySqrt(8));
//        System.out.println("平方根取整" + mySqrt(4));
        System.out.println("平方根取整" + isPerfectSquare(4));
        System.out.println("平方根取整" + isPerfectSquare(8));
        System.out.println("平方根取整" + isPerfectSquare(16));
    }

    /**
     * left>right 1. 上一个left+1 -> m<t 取完后 left > t 2. 上一个right-1 -> m>t 仍然如此
     */
    // 35. 搜索插入位置
    public static int binarySearch(int[] arr, int target) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int m = (left + right) >>> 1;
            if (arr[m] > target) {
                right = m - 1;
            } else if (arr[m] < target) {
                left = m + 1;
            } else {
                return m;
            }
        }
        return left;
    }

    /**
     * 情况一：target 在数组范围的右边或者左边，例如数组{3, 4, 5}，target为2或者数组{3, 4, 5},target为6，此时应该返回{-1, -1}
     * 情况二：target 在数组范围中，且数组中不存在target，例如数组{3,6,7},target为5，此时应该返回{-1, -1}
     * 情况三：target 在数组范围中，且数组中存在target，例如数组{3,6,7},target为6，此时应该返回{1, 1}
     */
    // 34. 查找元素的位置
    public static int[] searchRange(int[] arr, int target) {
        // 获取左边界
        int leftBorder = getLeftBorder(arr, target);
        // 获取右边界
        int rightBorder = getRightBorder(arr, target);
        // 情况一
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        // 情况三
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二
        return new int[]{-1, -1};
    }

    public static int getRightBorder(int[] arr, int target) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int rightBoder = -2;
        while (left <= right) {
            int m = (left + right) >>> 1;
            if (arr[m] > target) {
                right = m - 1;
            } else {
                left = m + 1;
                rightBoder = left;
            }
        }
        return rightBoder;
    }

    public static int getLeftBorder(int[] arr, int target) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int leftBoder = -2;
        while (left <= right) {
            int m = (left + right) >>> 1;
            if (arr[m] >= target) {
                right = m - 1;
                leftBoder = right;
            } else {
                left = m + 1;
            }
        }
        return leftBoder;
    }

    // 获取的是>=target的第一个数的下标
    // left>right 1. 上一个left+1 -> m<t 取完后 left >=t 2. 上一个right-1 -> m>=t 仍然如此
    // 如果数组为空，或者所有数都 < target，则返回 nums.length
    // 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
    // 34. 查找元素的位置
    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) { // 将mid往左边逼近
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 69. x 的平方根
    private static final int SQRT_INT_MAX = (int) Math.sqrt(Integer.MAX_VALUE);

    public static int mySqrt(int x) {
        int left = 0;
        int right = Math.min(x, SQRT_INT_MAX);
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int value = mid * mid;
            if (value > x) {
                right = mid - 1;
            } else if (value < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right; // v[r]<x
    }
    // 367. 有效的完全平方数
    public static boolean isPerfectSquare(int num) {
        int left = 0;
        int right = Math.min(num, SQRT_INT_MAX);
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int value = mid * mid;
            if (value > num) {
                right = mid - 1;
            } else if (value < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

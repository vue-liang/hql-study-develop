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
        System.out.println(Arrays.toString(searchRange(arr, 11)));
        System.out.println(Arrays.toString(searchRange(arr1, 8)));
    }

    /**
     * 为什么直接return left；
     * 因为如果上面的没有返回return middle，说明最后一定是，left>right
     * 从而跳出循环的，在此之前是left=right，如果最后是right-1导致的left>right，
     * 说明原来的right位置是大于target的，所以返回原来的right位置即left位置；
     * 如果最后是left+1导致的left>right,说明是原来的的left=right这个位置小于target，
     * 而right能移动到这个位置，说明此位置右侧是大于target的，
     * left现在加1就移动到了这样的位置，返回left即可
     */
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
}

package com.liang.leetcode.window;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 滑动窗口最大值 TODO 还没有完全弄懂
public class Lc239 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }

    private static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int[] arr = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            if (i > 0 && queue.peekFirst() == nums[i - 1]) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            if (i >= 0)
                arr[i] = queue.peekFirst();
        }
        return arr;
    }
}
